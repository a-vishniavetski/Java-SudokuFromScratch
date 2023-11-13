package org.example;

import java.util.Objects;

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
        return "SudokuField{" +
                "value=" + value +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
