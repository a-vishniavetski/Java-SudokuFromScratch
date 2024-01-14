package org.example;

public class SudokuBoardDaoFactory {

    public Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
    public Dao<SudokuBoard> getDBDao(String dbName) {
        return new JdbcSudokuBoardDao(dbName);
    }
}
