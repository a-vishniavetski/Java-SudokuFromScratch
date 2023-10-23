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
        Set<Integer> number_set = new HashSet<>();
        ArrayList<Integer> number_list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            number_set.add(array[i].getFieldValue());
            number_list.add(array[i].getFieldValue());
        }
        return number_set.size() == number_list.size();
    }
}
