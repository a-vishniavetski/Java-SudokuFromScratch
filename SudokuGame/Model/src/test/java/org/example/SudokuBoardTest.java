package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.beans.PropertyChangeListener;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class SudokuBoardTest {

    // testuje czy GetRow zwraca poprawną tablicę
    @Test
    public void GetRowTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        for (int i = 0; i < 9; i++) {
            SudokuRow row = board.getRow(i);
            for (int j = 0; j < 9; j++) {
                assertEquals(board.get(i, j), row.array.get(j).getFieldValue());
            }
        }
    }


    // testuje czy GetColumn zwraca poprawną tablicę
    @Test
    public void GetColumnTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        for (int i = 0; i < 9; i++) {
            SudokuColumn column = board.getColumn(i);
            for (int j = 0; j < 9; j++) {
                assertEquals(board.get(j, i), column.array.get(j).getFieldValue());
            }
        }
    }

    // testuje czy GetBox zwraca poprawną tablicę
    @Test
    public void GetBoxTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9 ; j += 3) {
                SudokuBox box = board.getBox(i, j);
                int k = 0;
                for (int x = i; x < i + 3; x++) {
                    for (int y = j; y < j + 3; y++) {
                        assertEquals(board.get(x, y), box.array.get(k).getFieldValue());
                        k++;
                    }
                }
            }
        }
    }


    // testuje czy wygenerowana przez FillBoard tablica jest poprawna
    @Test
    public void SolveGameTest(){
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // ----- WIERZE -----
        for (int y = 0; y < 9; y++){
            assertTrue(board.getRow(y).verify());
        }

        // ----- KOLUMNY -----
        for (int x = 0; x < 9; x++){
            assertTrue(board.getColumn(x).verify());
        }

        // ----- KWADRATY 3x3 -----
        for (int x = 0; x < 9; x += 3) {
            for (int y = 0; y < 9; y += 3) {
                assertTrue(board.getBox(x, y).verify());
            }
        }
    }

    // testuje czy FillBoard generuje rózne tablice przy kolejnych wywołaniach
    @Test
    public void DifferenceSolveGameTest(){
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);

        int[][] board1 = new int[9][9];
        int[][] board2 = new int[9][9];

        board.solveGame();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9 ; j++) {
                board1[i][j] = board.get(i, j);
            }
        }

        board.solveGame();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board2[i][j] = board.get(i, j);
            }
        }

        // sprawdzamy czy tablice są równe
        boolean has_different_elements = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board1[i][j] != board2[i][j]){
                    has_different_elements = true;
                    break;
                }
            }
        }
        assertTrue(has_different_elements);

    }

    @Test
    public void AddRemoveListenerTest() {
        // tworzymy deskę
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);

        // dodajemy listenera
        SudokuBoardChangeListener listener = new SudokuBoardChangeListener();
        board.addPropertyChangeListener(listener);

        // sprawdzamy czy on się dodał
        PropertyChangeListener[] added_listeners = board.propertyChangeSupport.getPropertyChangeListeners();

        assertTrue(added_listeners.length == 1);
        assertTrue(added_listeners[0] == listener);

        // sprawdzamy czy można jego usuwać
        board.removePropertyChangeListener(listener);
        added_listeners = board.propertyChangeSupport.getPropertyChangeListeners();

        assertTrue(added_listeners.length == 0);
    }

    @Test
    public void ToStringTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        assertNotNull(board.toString());
    }

    @Test
    public void HashCodePositiveTest() {
        // tworzymy dwie takie same plansze
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(sudokuSolver);
        SudokuBoard board2 = new SudokuBoard(sudokuSolver);
        assertEquals(board1.hashCode(), board2.hashCode());
    }

    @Test
    public void HashCodeNegativeTest() {
        // tworzymy dwie takie różne plansze
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(sudokuSolver);
        SudokuBoard board2 = new SudokuBoard(sudokuSolver);
        board1.solveGame();
        board2.solveGame();

        assertNotEquals(board1.hashCode(), board2.hashCode());
    }

    @Test
    public void EqualsSameObjectTest() {
        // ten sam obiekt
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        boolean result = board.equals(board);
        assertTrue(result);

        assertEquals(board.hashCode(), board.hashCode()); // ten sam hashCode
    }

    @Test
    public void EqualsNullTest() {
        // null
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        boolean result = board.equals(null);

        assertFalse(result);
    }

    @Test
    public void EqualsDifferentClassTest() {
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        Object o = new Object(); // inna klasa

        boolean result = board.equals(o);
        assertFalse(result);
    }

    @Test
    public void NegativeEqualsTest() {
        // różne plansze
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(sudokuSolver);
        SudokuBoard board2 = new SudokuBoard(sudokuSolver);
        board1.solveGame();
        board2.solveGame();

        boolean result = board1.equals(board2);
        assertFalse(result);

        // hashcode
        assertNotEquals(board1.hashCode(), board2.hashCode());
    }

    @Test
    public void EqualsPositiveTest() {
        // tworzymy dwa obiekty SudokuBoard z takimi samymi wartościami SudokuField
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board1 = new SudokuBoard(sudokuSolver);
        SudokuBoard board2 = new SudokuBoard(sudokuSolver);

        // sprawdzamy czy equals zwraca true
        boolean result = board1.equals(board2);
        assertTrue(result);

        // hashcode
        assertEquals(board1.hashCode(), board2.hashCode());
    }

    @Test
    public void PositiveCloneTest() {
        // tworzymy obiekt
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // klonujemy
        SudokuBoard clonedBoard = null;
        try {
            clonedBoard = board.clone();
        } catch (CloneNotSupportedException e) {
            fail("CloneNotSupportedException");
        }

        // sprawdzamy czy są takie same
        assertEquals(board, clonedBoard);

        // sprawdzamy czy nie są tym samym obiektem
        assertNotSame(board, clonedBoard);

        // sprawdzamy czy mają takie same hashe
        assertEquals(board.hashCode(), clonedBoard.hashCode());
    }

    // sprawdzamy czy robimy DeepCopy czy ShallowCopy, czyli czy tworzymy nowe obiekty czy używamy ich referencji
    @Test
    public void ReferenceCloneTest() {
        // tworzymy obiekt
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // klonujemy
        SudokuBoard clonedBoard = null;
        try {
            clonedBoard = board.clone();
        } catch (CloneNotSupportedException e) {
            fail("CloneNotSupportedException");
        }

        // zmieniamy skopiowany obiekt
        clonedBoard.set(0, 0, 5);
        clonedBoard.set(0, 1, 5);
        clonedBoard.set(0, 2, 5);

        // sprawdzamy czy zmienił się oryginał
        assertNotEquals(board, clonedBoard);
    }

    @Test
    public void ClearRandomFieldsEasyTest() {
        // tworzymy obiekt
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // EASY
        board.clearRandomFields(Difficulty.EASY);

        // sprawdzamy czy są 5 pól pustych
        int emptyFields = 0;
        for (int i = 0; i < 9; i++) {
            SudokuRow row = board.getRow(i);
            for (int j = 0; j < 9; j++) {
                if (row.array.get(j).getFieldValue() == 0) {
                    emptyFields++;
                }
            }
        }

        assertEquals(5, emptyFields);
    }

    @Test
    public void ClearRandomFieldsNormalTest() {
        // tworzymy obiekt
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // NORMAL
        board.clearRandomFields(Difficulty.NORMAL);

        // sprawdzamy czy są 10 pól pustych
        int emptyFields = 0;
        for (int i = 0; i < 9; i++) {
            SudokuRow row = board.getRow(i);
            for (int j = 0; j < 9; j++) {
                if (row.array.get(j).getFieldValue() == 0) {
                    emptyFields++;
                }
            }
        }

        assertEquals(10, emptyFields);
    }

    @Test
    public void ClearRandomFieldsHardTest() {
        // tworzymy obiekt
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // NORMAL
        board.clearRandomFields(Difficulty.HARD);

        // sprawdzamy czy są 15 pól pustych
        int emptyFields = 0;
        for (int i = 0; i < 9; i++) {
            SudokuRow row = board.getRow(i);
            for (int j = 0; j < 9; j++) {
                if (row.array.get(j).getFieldValue() == 0) {
                    emptyFields++;
                }
            }
        }

        assertEquals(15, emptyFields);
    }

    @Test
    public void ClearRandomFieldsDefaultTest() {
        // tworzymy obiekt
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();

        // sprawdzamy czy są 0 pól pustych
        int emptyFields = 0;
        for (int i = 0; i < 9; i++) {
            SudokuRow row = board.getRow(i);
            for (int j = 0; j < 9; j++) {
                if (row.array.get(j).getFieldValue() == 0) {
                    emptyFields++;
                }
            }
        }

        assertEquals(0, emptyFields);
    }
}
