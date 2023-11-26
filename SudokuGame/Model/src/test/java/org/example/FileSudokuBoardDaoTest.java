package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.PropertyChangeListener;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class FileSudokuBoardDaoTest {

    @Test
    public void PositiveWriteReadTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // zapisujemy tablice używając try-with-resources
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        try (Dao<SudokuBoard> dao = factory.getFileDao("saved_board");) {
            dao.write(board);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // odczytujemy tablice używając try-with-resources i porównujemy z oryginałem
        try (Dao<SudokuBoard> dao = factory.getFileDao("saved_board");) {
            SudokuBoard readBoard = dao.read();
            assertEquals(board, readBoard);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void NegativeWriteReadTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // zapisujemy tablice używając try-with-resources
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        try (Dao<SudokuBoard> dao = factory.getFileDao("saved_board");) {
            dao.write(board);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // odczytujemy tablice używając try-with-resources i porównujemy z oryginałem
        try (Dao<SudokuBoard> dao = factory.getFileDao("saved_board");) {
            SudokuBoard readBoard = dao.read();
            readBoard.set(0, 0, 0);
            assertNotEquals(board, readBoard);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void ReadRuntimeExceptionTest() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

        // probujemy odczytać nie istniejący plik
        Exception exception = assertThrows(RuntimeException.class, () -> {
            try (Dao<SudokuBoard> dao = factory.getFileDao("not_saved_board");) {
                SudokuBoard readBoard = dao.read();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }
}
