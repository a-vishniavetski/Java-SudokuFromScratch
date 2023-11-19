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
        StringBuilder builder = new StringBuilder();
        builder.append("SudokuField = {");
        builder.append(getFieldValue());
        builder.append("}");
        return builder.toString();
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
