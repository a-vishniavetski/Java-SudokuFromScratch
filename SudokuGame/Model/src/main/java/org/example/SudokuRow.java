package org.example;

import java.util.List;

public class SudokuRow extends SudokuElement implements Cloneable {
    public SudokuRow(List<SudokuField> array) {
        super(array);
    }
}
