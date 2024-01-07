package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class SudokuExceptionTest {

    @Test
    public void IOexceptionTest() {
        Exception exception = assertThrows(SudokuIOexception.class, () -> {
            throw new SudokuIOexception("IOError", new Exception());
        });
    }

    @Test
    public void CloneExceptionTest() {
        Exception exception = assertThrows(SudokuCloneException.class, () -> {
            throw new SudokuCloneException("CloneError", new Exception());
        });
    }

    @Test
    public void WriteExceptionTest() {
        Exception exception = assertThrows(SudokuWriteException.class, () -> {
            throw new SudokuWriteException("WriteError", new Exception());
        });
    }
}
