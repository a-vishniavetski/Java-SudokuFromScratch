package org.example;

import java.util.*;
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
        //SudokuField[] RowArray = new SudokuField[9]; stara implementacja
        List<SudokuField> RowArray = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            RowArray.set(i, new SudokuField(i));
        }
        SudokuElement RowElement = new SudokuRow(RowArray);
        assertTrue(RowElement.verify());

        // sudokuColumn
        List<SudokuField> ColumnArray = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            ColumnArray.set(i, new SudokuField(i));
        }
        SudokuElement ColumnElement = new SudokuColumn(ColumnArray);
        assertTrue(ColumnElement.verify());

        // SudokuBox
        List<SudokuField> BoxArray = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            BoxArray.set(i, new SudokuField(i));
        }
        SudokuElement BoxElement = new SudokuBox(BoxArray);
        assertTrue(BoxElement.verify());
    }


    // negatywna nest metody verify dla SudokuRow, SudokuColumn i SudokuBox
    @Test
    public void NegativeVerifyTest() {
        // SudokuRow
        List<SudokuField> RowArray = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            RowArray.set(i, new SudokuField(i));
        }

        // zmieniamy dwa elementy na takie same
        RowArray.set(0, new SudokuField(1));
        RowArray.set(1, new SudokuField(1));

        SudokuElement RowElement = new SudokuRow(RowArray);
        assertFalse(RowElement.verify());

        // SudokuColumn
        List<SudokuField> ColumnArray = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            ColumnArray.set(i, new SudokuField(i));
        }
        ColumnArray.set(0, new SudokuField(1));
        ColumnArray.set(1, new SudokuField(1));

        SudokuElement ColumnElement = new SudokuColumn(ColumnArray);
        assertFalse(ColumnElement.verify());

        // sudokuBox
        List<SudokuField> BoxArray = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            BoxArray.set(i, new SudokuField(i));
        }
        BoxArray.set(0, new SudokuField(1));
        BoxArray.set(1, new SudokuField(1));

        SudokuElement BoxElement = new SudokuBox(BoxArray);
        assertFalse(BoxElement.verify());
    }
}

