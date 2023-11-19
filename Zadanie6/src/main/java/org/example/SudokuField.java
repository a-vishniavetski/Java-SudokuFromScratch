package org.example;

import java.util.Objects;

import static junit.framework.Assert.assertEquals;

public class SudokuField {
    int value;

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return this.value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SudokuField{"
                + "value="
                + value
                + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuField comparedObject = (SudokuField) o;

        return value == comparedObject.value;
    }

}
