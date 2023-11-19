package org.example;

import java.util.*;

abstract class SudokuElement {

    //public SudokuField[] array = new SudokuField[9];
    public List<SudokuField> array = Arrays.asList(new SudokuField[9]);

    public SudokuElement(List<SudokuField> array) {
        this.array = array;
    }

    public boolean verify() {
        Set<Integer> numberSet = new HashSet<>();
        ArrayList<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            numberSet.add(array.get(i).getFieldValue());
            numberList.add(array.get(i).getFieldValue());
        }

        // usuwamy 0 z listy i setu
        numberList.removeIf(n -> n == 0);
        numberSet.removeIf(n -> n == 0);

        return numberSet.size() == numberList.size();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SudokuElement = {");
        for (int i = 0; i < 9; i++) {
            builder.append(array.get(i).getFieldValue());
            if (i != 8) {
                builder.append(", ");
            }
        }
        builder.append("}");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(array);
    }

    @Override
    public boolean equals(Object o) {
        // ten samy adres w pamięci
        if (this == o) {
            return true;
        }
        // null lub różne klasy
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        // sprawdzamy hashCode
        SudokuElement comparedObject = (SudokuElement) o;
        if (this.hashCode() != comparedObject.hashCode()) {
            return false;
        }

        // sprawdzamy czy wszystkie pola są takie same
        for (int i = 0; i < 9; i++) {
            if (array.get(i).getFieldValue() != comparedObject.array.get(i).getFieldValue()) {
                return false;
            }
        }

        return true;
    }
}
