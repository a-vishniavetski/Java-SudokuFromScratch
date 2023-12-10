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

    @Test
    public void ConstructorTest() {
        assertDoesNotThrow(() -> {
            SudokuRow row = new SudokuRow(Arrays.asList(new SudokuField[9]));
            SudokuColumn column = new SudokuColumn(Arrays.asList(new SudokuField[9]));
            SudokuBox box = new SudokuBox(Arrays.asList(new SudokuField[9]));
        });
    }

    @Test
    public void ToStringTest() {
        // tworzymy SudokuRow, bo SudokuElement to klasa abstrakcyjna
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i, new SudokuField(i));
        }
        SudokuElement element = new SudokuRow(fields);

        // sprawdzamy działanie metody toString
        String expectedOutput = "SudokuElement = {0, 1, 2, 3, 4, 5, 6, 7, 8}";
        assertEquals(expectedOutput, element.toString());
    }

    @Test
    public void PositiveHashCodeTest() {
        // tworzymy dwa obiekty SudokuRow z takimi samymi wartościami SudokuField
        List<SudokuField> fields1 = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields1.set(i, new SudokuField(i));
        }
        SudokuElement element1 = new SudokuRow(fields1);

        List<SudokuField> fields2 = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields2.set(i, new SudokuField(i));
        }
        SudokuElement element2 = new SudokuRow(fields2);

        // sprawdzamy czy hashCode jest taki sam
        assertEquals(element1.hashCode(), element2.hashCode());
    }

    @Test
    public void NegativeHashCodeTest() {
        // tworzymy dwa obiekty SudokuRow o różnych wartościach SudokuField
        List<SudokuField> fields1 = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields1.set(i, new SudokuField(i));
        }
        SudokuElement element1 = new SudokuRow(fields1);

        List<SudokuField> fields2 = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields2.set(i, new SudokuField(i + 1)); // inne wartości
        }
        SudokuElement element2 = new SudokuRow(fields2);

        // sprawdzamy czy hashCode jest różny
        assertNotEquals(element1.hashCode(), element2.hashCode());
    }

    @Test
    public void PositiveEqualsTest() {
        // tworzymy dwa obiekty SudokuRow z takimi samymi wartościami SudokuField
        List<SudokuField> fields1 = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields1.set(i, new SudokuField(i));
        }
        SudokuElement element1 = new SudokuRow(fields1);

        List<SudokuField> fields2 = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields2.set(i, new SudokuField(i));
        }
        SudokuElement element2 = new SudokuRow(fields2);

        // sprawdzamy czy equals zwraca true
        boolean result = element1.equals(element2);
        assertTrue(result);
    }

    @Test
    public void NegativeEqualsTest() {
        // tworzymy dwa obiekty SudokuRow o różnych wartościach SudokuField
        List<SudokuField> fields1 = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields1.set(i, new SudokuField(i));
        }
        SudokuElement element1 = new SudokuRow(fields1);

        List<SudokuField> fields2 = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields2.set(i, new SudokuField(i + 1)); // inne wartości
        }
        SudokuElement element2 = new SudokuRow(fields2);

        // sprawdzamy czy equals zwraca false
        boolean result = element1.equals(element2);
        assertFalse(result);
    }

    @Test
    public void EqualsSameObjectTestEnd() {
        // ten sam obiekt
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i, new SudokuField(i));
        }
        SudokuElement element = new SudokuRow(fields);
        boolean result = element.equals(element);
        assertTrue(result);
        assertEquals(element.hashCode(), element.hashCode()); // ten sam hashCode
    }

    @Test
    public void EqualsNullTest() {
        // null
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i, new SudokuField(i));
        }
        SudokuElement element = new SudokuRow(fields);
        boolean result = element.equals(null); // null
        assertFalse(result);
    }

    @Test
    public void EqualsDifferentClassTest() {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i, new SudokuField(i));
        }
        SudokuElement element = new SudokuRow(fields);
        Object o = new Object(); // inna klasa

        boolean result = element.equals(o);
        assertFalse(result);
    }

    // --------- CLONE TESTY ---------
    @Test
    public void PositiveRowCloneTest() {
        // tworzymy obiekt SudokuRow
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i, new SudokuField(i));
        }
        SudokuElement element = new SudokuRow(fields);

        // klonujemy obiekt
        SudokuElement clonedElement = element.clone();

        // sprawdzamy czy wartości PÓL są takie same
        assertEquals(element, clonedElement);
    }

    // sprawdzamy czy robimy DeepCopy czy ShallowCopy, czyli czy tworzymy nowe obiekty czy używamy ich referencji
    @Test
    public void ReferenceRowCloneTest() {
        // tworzymy obiekt SudokuRow
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i, new SudokuField(i));
        }
        SudokuElement element = new SudokuRow(fields);

        // klonujemy obiekt
        SudokuElement clonedElement = element.clone();

        // zmieniamy sklonowany obiekt
        clonedElement.array.set(0, new SudokuField(5));
        clonedElement.array.set(1, new SudokuField(5));

        // sprawdzamy czy zmienił się oryginał
        assertNotEquals(element, clonedElement);
    }

    @Test
    public void PositiveBoxCloneTest() {
        // tworzymy obiekt SudokuBox
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i, new SudokuField(i));
        }
        SudokuElement element = new SudokuBox(fields);

        // klonujemy obiekt
        SudokuElement clonedElement = element.clone();

        // sprawdzamy czy wartości PÓL są takie same
        assertEquals(element, clonedElement);
    }

    // sprawdzamy czy robimy DeepCopy czy ShallowCopy, czyli czy tworzymy nowe obiekty czy używamy ich referencji
    @Test
    public void ReferenceBoxCloneTest() {
        // tworzymy obiekt SudokuBox
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            fields.set(i, new SudokuField(i));
        }
        SudokuElement element = new SudokuBox(fields);

        // klonujemy obiekt
        SudokuElement clonedElement = element.clone();

        // zmieniamy sklonowany obiekt
        clonedElement.array.set(0, new SudokuField(5));
        clonedElement.array.set(1, new SudokuField(5));

        // sprawdzamy czy zmienił się oryginał
        assertNotEquals(element, clonedElement);
    }

    // sprawdzamy czy robimy DeepCopy czy ShallowCopy, czyli czy tworzymy nowe obiekty czy używamy ich referencji
    @Test
    public void PositiveColumnCloneTest() {
        // tworzymy obiekt SudokuColumn
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) { // wypełniamy SudokuField
            fields.set(i, new SudokuField(i));
        }
        SudokuElement element = new SudokuColumn(fields);

        // klonujemy obiekt
        SudokuElement clonedElement = element.clone();

        // sprawdzamy czy wartości PÓL są takie same
        assertEquals(element, clonedElement);
    }

    // sprawdzamy czy robimy DeepCopy czy ShallowCopy, czyli czy tworzymy nowe obiekty czy używamy ich referencji
    @Test
    public void ReferenceColumnCloneTest() {
        // tworzymy obiekt SudokuColumn
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) { // wypełniamy SudokuField
            fields.set(i, new SudokuField(i));
        }
        SudokuElement element = new SudokuColumn(fields);

        // klonujemy obiekt
        SudokuElement clonedElement = element.clone();

        // zmieniamy sklonowany obiekt
        clonedElement.array.set(0, new SudokuField(5));
        clonedElement.array.set(1, new SudokuField(5));

        // sprawdzamy czy zmienił się oryginał
        assertNotEquals(element, clonedElement);
    }
}

