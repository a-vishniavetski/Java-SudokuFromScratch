package org.example;

public interface Dao<T> extends AutoCloseable {
    T read() throws SudokuReadException;

    void write(T obj) throws SudokuWriteException;
}
