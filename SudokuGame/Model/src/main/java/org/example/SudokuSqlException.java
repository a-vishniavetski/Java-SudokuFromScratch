package org.example;

public class SudokuSqlException extends SudokuIoException {
    public SudokuSqlException(String message, Throwable cause) {
        super(message, cause);
    }
}