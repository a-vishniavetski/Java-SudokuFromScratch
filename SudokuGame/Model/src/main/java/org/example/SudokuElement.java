package org.example;

import java.util.*;
import java.util.logging.Logger;

import static java.lang.System.exit;

abstract class SudokuElement implements Cloneable {

    //LOGGING
    private static final Logger ElementLogger = Logger.getLogger(SudokuElement.class.getName());

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

        SudokuElement comparedObject = (SudokuElement) o;

        // sprawdzamy czy wszystkie pola są takie same
        for (int i = 0; i < 9; i++) {
            if (array.get(i).getFieldValue() != comparedObject.array.get(i).getFieldValue()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public SudokuElement clone() {
        try {
            // towrzymy nowy array i kopiujemy do niego wszystkie pola
            List<SudokuField> clonedArray = Arrays.asList(new SudokuField[9]);
            for (int i = 0; i < 9; i++) {
                SudokuField thisObjectField = array.get(i);
                clonedArray.set(i, thisObjectField.clone());
            }

            // klonujemy obiekt i ustawiamy mu nowy array
            SudokuElement clonedObject = (SudokuElement) super.clone();
            clonedObject.array = clonedArray;
            return clonedObject;

        } catch (CloneNotSupportedException e) {
            try {
                throw new SudokuCloneException("CloneError", e);
            } catch (SudokuCloneException ex) {
                ElementLogger.info(ex.getMessage());
                exit(1);
            }
        }
        return null;
    }
}
