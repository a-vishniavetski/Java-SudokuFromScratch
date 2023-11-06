package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

abstract class SudokuElement {

    public SudokuField[] array = new SudokuField[9];

    public SudokuElement(SudokuField[] array) {
        this.array = array;
    }

    public boolean verify() {
        Set<Integer> numberSet = new HashSet<>();
        ArrayList<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            numberSet.add(array[i].getFieldValue());
            numberList.add(array[i].getFieldValue());
        }

        // usuwamy 0 z listy i setu
        numberList.removeIf(n -> (n == 0));
        numberSet.removeIf(n -> (n == 0));

        return numberSet.size() == numberList.size();
    }
}
