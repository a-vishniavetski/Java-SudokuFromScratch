package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.PropertyChangeListener;
import java.util.Set;
import java.util.HashSet;
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
