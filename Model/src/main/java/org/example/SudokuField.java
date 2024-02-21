package org.example;

import java.io.Serializable;
import java.util.Objects;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
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

    @Override
    public int compareTo(SudokuField o) throws NullPointerException {
        // null
        if (o == null) {
            throw new NullPointerException();
        }

        if (this.getFieldValue() < o.getFieldValue()) {
            // wartość tego pola jest mniejsza
            return -1;
        } else if (this.getFieldValue() > o.getFieldValue()) {
            // wartość tego pola jest większa
            return 1;
        } else {
            // wartość tego pola jest taka sama
            return 0;
        }
    }

    @Override
    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }
}
