package org.example;

import java.sql.*;
import java.util.ArrayList;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {

    private String dbName;

    private String connectionString;

    public JdbcSudokuBoardDao(String dbName, String connectionString) {
        this.dbName = dbName;
        this.connectionString = connectionString;
    }

    private Connection connect() throws SudokuSqlException, SudokuClassNotFoundException {
        //Class.forName("com.mysql.jdbc.Driver");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SudokuClassNotFoundException("Class not found", e);
        }

        //return DriverManager.getConnection("jdbc:mysql://localhost:3306/sudoku", "root", "");
        try {
            return DriverManager.getConnection(this.connectionString, "root", "");
        } catch (SQLException e) {
            throw new SudokuSqlException("SQL error", e);
        }
    }

    public ArrayList<String> getAllBoardNames() {
        ArrayList<String> names = new ArrayList<>();

        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name from boards WHERE name NOT LIKE \"%_primal%\"");
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return names;
    }

    @Override
    public SudokuBoard read() throws SudokuReadException {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();

            board.solveGame();

            ResultSet select = stmt.executeQuery(String.format("SELECT id FROM boards where name = \"%s\"", dbName));
            int boardID = 0;
            if (select.next()) {
                boardID = select.getInt("id");
            } else {
                throw new SudokuReadException("No save board in DB", new Exception());
            }

            ResultSet fields = stmt.executeQuery(
                    String.format("SELECT x, y, value FROM fields WHERE board_id = \"%s\"", boardID));
            while (fields.next()) {
                int val = fields.getInt("value");
                int x = fields.getInt("x");
                int y = fields.getInt("y");
                board.set(x, y, val);
            }

        } catch (Exception e) {
            throw new SudokuReadException("Read error", e);
        }
        return board;
    }

    @Override
    public void write(SudokuBoard obj) throws SudokuWriteException {
        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(String.format("SELECT id, name FROM boards WHERE name = \"%s\"", dbName));
            int boardID = 0;
            if (res.next()) {
                boardID = res.getInt("id");
            } else {
                int result = stmt.executeUpdate(String.format("INSERT INTO boards(name) VALUES (\"%s\")", dbName));
                if (result == 1) {
                    ResultSet getId = stmt.executeQuery(
                            String.format("SELECT id FROM boards WHERE name = \"%s\"", dbName));
                    if (getId.next()) {
                        boardID = getId.getInt("id");
                    }
                }
            }
            res = stmt.executeQuery(String.format("SELECT id FROM fields WHERE board_id = \"%s\"", boardID));
            if (res.next()) {
                stmt.executeUpdate(String.format("DELETE FROM fields WHERE board_id = %s", boardID));
            }
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    int fieldValue = obj.get(x, y);
                    stmt.addBatch(
                            String.format(
                                    "INSERT INTO fields(x, y, value, board_id) VALUES (%s, %s, %s, %s)",
                                    x, y, fieldValue, boardID));
                }
            }
            int[] insert = stmt.executeBatch();
            //System.out.println("Write complete");
        } catch (Exception e) {
            throw new SudokuWriteException("Write error", e);
        }
    }

    @Override
    public void close() throws Exception {

    }
}
