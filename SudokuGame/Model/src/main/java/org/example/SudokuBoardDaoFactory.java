package org.example;

public class SudokuBoardDaoFactory {

    public Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public Dao<SudokuBoard> getDbDao(String dbName, String connectionString) {
        return new JdbcSudokuBoardDao(dbName, connectionString);
    }
}
