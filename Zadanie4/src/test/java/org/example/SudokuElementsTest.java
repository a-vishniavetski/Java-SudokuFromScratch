package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class SudokuElementsTest {

    // pozytywny test metody verify dla SudokuRow, SudokuColumn i SudokuBox
    @Test
    public void PositiveVerifyTest(){
        // SudokuRow
        SudokuField[] RowArray = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            RowArray[i] = new SudokuField(i);
        }
        SudokuElement RowElement = new SudokuRow(RowArray);
        assertTrue(RowElement.verify());

        // sudokuColumn
        SudokuField[] ColumnArray = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            ColumnArray[i] = new SudokuField(i);
        }
        SudokuElement ColumnElement = new SudokuColumn(ColumnArray);
        assertTrue(ColumnElement.verify());

        // SudokuBox
        SudokuField[] BoxArray = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            BoxArray[i] = new SudokuField(i);
        }
        SudokuElement BoxElement = new SudokuBox(BoxArray);
        assertTrue(BoxElement.verify());
    }


    // negatywna nest metody verify dla SudokuRow, SudokuColumn i SudokuBox
    @Test
    public void NegativeVerifyTest() {
        // SudokuRow
        SudokuField[] RowArray = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            RowArray[i] = new SudokuField(i);
        }

        // zmieniamy dwa elementy na takie same
        RowArray[0] = new SudokuField(1);
        RowArray[1] = new SudokuField(1);

        SudokuElement RowElement = new SudokuRow(RowArray);
        assertFalse(RowElement.verify());

        // SudokuColumn
        SudokuField[] ColumnArray = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            ColumnArray[i] = new SudokuField(i);
        }
        ColumnArray[0] = new SudokuField(1);
        ColumnArray[1] = new SudokuField(1);

        SudokuElement ColumnElement = new SudokuColumn(ColumnArray);
        assertFalse(ColumnElement.verify());

        // sudokuBox
        SudokuField[] BoxArray = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            BoxArray[i] = new SudokuField(i);
        }
        BoxArray[0] = new SudokuField(1);
        BoxArray[1] = new SudokuField(1);

        SudokuElement BoxElement = new SudokuBox(BoxArray);
        assertFalse(BoxElement.verify());
    }
}

