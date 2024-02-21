package org.example;

import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {

    @Test
    public void PositiveEqualsTest() {
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = new SudokuField(5);
        boolean result = field1.equals(field2);
        assertTrue(result);

        // hashcode
        assertEquals(field1.hashCode(), field2.hashCode());
    }

    @Test
    public void NegativeEqualsTest() {
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = new SudokuField(6); // inna wartość
        boolean result = field1.equals(field2);
        assertFalse(result);

        // hashcode
        assertNotEquals(field1.hashCode(), field2.hashCode());
    }

    @Test
    public void EqualsSameObjectTest() {
        SudokuField field = new SudokuField(5);
        boolean result = field.equals(field); // ten sam obiekt
        assertTrue(result);

        // hashcode
        assertEquals(field.hashCode(), field.hashCode());
    }

    @Test
    public void EqualsNullTest() {
        SudokuField field = new SudokuField(5);
        SudokuField nullField = null;
        boolean result = field.equals(nullField);
        assertFalse(result);
    }

    @Test
    public void EqualsDifferentClassTest() {
        SudokuField field = new SudokuField(5);
        Object o = new Object(); // inna klasa
        boolean result = field.equals(o);
        assertFalse(result);
    }
    @Test
    public void PositiveHashCodeTest() {
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = new SudokuField(5);
        assertEquals(field1.hashCode(), field2.hashCode());
    }

    @Test
    public void NegativeHashCodeTest() {
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = new SudokuField(6); // inna wartosc
        assertNotEquals(field1.hashCode(), field2.hashCode());
    }

    @Test
    public void ToStringTest() {
        SudokuField field = new SudokuField(5);
        String expectedOutput = "SudokuField = {5}";
        assertEquals(expectedOutput, field.toString());
    }

    @Test
    public void SmallerCompareToTest() {
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = new SudokuField(6);

        // field1 jest mniejszy od field2, więc compareTo zwraca -1
        int result = field1.compareTo(field2);
        assertEquals(-1, result);
    }

    @Test
    public void BiggerCompareToTest() {
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = new SudokuField(6);

        // field2 jest większy od field1, więc compareTo zwraca 1
        int result = field2.compareTo(field1);
        assertEquals(1, result);
    }

    @Test
    public void EqualCompareToTest() {
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = new SudokuField(5);

        // field1 jest równy field2, więc compareTo zwraca 0
        int result = field1.compareTo(field2);
        assertEquals(0, result);
    }

    @Test
    public void NullCompareToTest() {
        SudokuField field = new SudokuField(5);
        SudokuField nullField = null;

        // compareTo zwraca wyjątek NullPointerException
        assertThrows(NullPointerException.class, () -> {
            field.compareTo(nullField);
        });
    }

    @Test
    public void CloneTest() {
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = null;

        try {
            field2 = field1.clone();
        } catch (CloneNotSupportedException e) {
            fail("Clone not supported");
        }

        assertEquals(field1, field2);
    }
}