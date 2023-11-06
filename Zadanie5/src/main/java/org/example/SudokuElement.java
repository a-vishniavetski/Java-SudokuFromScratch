package org.example;

import java.util.*;

abstract class SudokuElement {

//    public SudokuField[] array = new SudokuField[9];
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
        numberList.removeIf(n -> (n == 0));
        numberSet.removeIf(n -> (n == 0));

        return numberSet.size() == numberList.size();
    }
}
