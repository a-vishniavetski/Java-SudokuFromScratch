package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class FileSudokuBoardDaoTest {

    @Test
    public void PositiveWriteReadTest() throws SudokuWriteException, SudokuReadException {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // zapisujemy tablice używając try-with-resources
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        try (Dao<SudokuBoard> dao = factory.getFileDao("saved_board");) {
            dao.write(board);
        } catch (Exception e) {
            throw new SudokuWriteException("WriteError", e);
        }

        // odczytujemy tablice używając try-with-resources i porównujemy z oryginałem
        try (Dao<SudokuBoard> dao = factory.getFileDao("saved_board");) {
            SudokuBoard readBoard = dao.read();
            assertEquals(board, readBoard);
        } catch (Exception e) {
            throw new SudokuReadException("ReadError", e);
        }
    }

    @Test
    public void NegativeWriteReadTest() throws SudokuWriteException, SudokuReadException {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // zapisujemy tablice używając try-with-resources
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        try (Dao<SudokuBoard> dao = factory.getFileDao("saved_board");) {
            dao.write(board);
        } catch (Exception e) {
            throw new SudokuWriteException("WriteError", e);
        }

        // odczytujemy tablice używając try-with-resources i porównujemy z oryginałem
        try (Dao<SudokuBoard> dao = factory.getFileDao("saved_board");) {
            SudokuBoard readBoard = dao.read();
            readBoard.set(0, 0, 0);
            assertNotEquals(board, readBoard);
        } catch (Exception e) {
            throw new SudokuReadException("ReadError", e);
        }
    }

    @Test
    public void ReadExceptionTest() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

        // probujemy odczytać nie istniejący plik
        Exception exception = assertThrows(SudokuReadException.class, () -> {
            try (Dao<SudokuBoard> dao = factory.getFileDao("not_saved_board");) {
                SudokuBoard readBoard = dao.read();
            } catch (Exception e) {
                throw new SudokuReadException("ReadError", e);
            }
        });
    }

    // ---------- TESTY DLA BAZY DANYCH ----------
    @Test
    public void DBWriteReadTest() throws SudokuWriteException, SudokuReadException {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // zapisujemy tablice używając try-with-resources
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        try (Dao<SudokuBoard> dao = factory.getDbDao("testBoard", "jdbc:mysql://localhost:3306/sudoku");) {
            dao.write(board);
        } catch (Exception e) {
            throw new SudokuWriteException("WriteError", e);
        }

        // odczytujemy tablice używając try-with-resources i porównujemy z oryginałem
        try (Dao<SudokuBoard> dao = factory.getDbDao("testBoard", "jdbc:mysql://localhost:3306/sudoku");) {
            SudokuBoard readBoard = dao.read();
            assertEquals(board, readBoard);
            // sprawdzamy czy jest innym obiektem
            assertNotSame(board, readBoard);
        } catch (Exception e) {
            throw new SudokuReadException("ReadError", e);
        }


    }

    @Test
    public void DBExceptionTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // probujemy zapisać plik do niepoprawnej connectionString
        Exception exception = assertThrows(SudokuWriteException.class, () -> {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            try (Dao<SudokuBoard> dao = factory.getDbDao("testBoard", "nonsenseString");) {
                dao.write(board);
            } catch (Exception e) {
                throw new SudokuWriteException("WriteError", e);
            }
        });

        // probujemy odczytać nie istniejący plik
        exception = assertThrows(SudokuReadException.class, () -> {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            try (Dao<SudokuBoard> dao = factory.getDbDao("not_saved_board", "jdbc:mysql://localhost:3306/sudoku");) {
                SudokuBoard readBoard = dao.read();
            } catch (Exception e) {
                throw new SudokuReadException("ReadError", e);
            }
        });
    }

    @Test
    public void DBGetAllBoardsTest() throws SudokuReadException {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        try (Dao<SudokuBoard> dao = factory.getDbDao("testBoard", "jdbc:mysql://localhost:3306/sudoku");) {
            ArrayList<String> names = new ArrayList<>();
            names = ((JdbcSudokuBoardDao) dao).getAllBoardNames();
            assertTrue(names.contains("testBoard"));
        } catch (Exception e) {
            throw new SudokuReadException("ReadError", e);
        }
    }

    @Test
    public void PrimalWriteReadTest() throws SudokuWriteException, SudokuReadException, CloneNotSupportedException {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        SudokuBoard oldBoard = board.clone();
        board.solveGame();

        // zapisujemy tablice używając try-with-resources
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        try (FileSudokuBoardDao dao = new FileSudokuBoardDao("saved_board");) {
            dao.write(board, oldBoard);
        } catch (Exception e) {
            throw new SudokuWriteException("WriteError", e);
        }

        // odczytujemy tablice używając try-with-resources i porównujemy z oryginałem
        try (FileSudokuBoardDao dao = new FileSudokuBoardDao("saved_board");) {
            ArrayList<SudokuBoard> boards = new ArrayList<>();
            boards = dao.readWithPrimal();
        } catch (Exception e) {
            throw new SudokuReadException("ReadError", e);
        }



    }

}
