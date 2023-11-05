package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

abstract class SudokuElement {

    SudokuField[] array = new SudokuField[9];

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
        return numberSet.size() == numberList.size();
    }
}
