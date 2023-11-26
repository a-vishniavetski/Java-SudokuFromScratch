package org.example;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


public class SudokuBoardChangeListener implements PropertyChangeListener, Serializable {

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("\nSudokuBoardChangeListener: ");
        List<List<SudokuField>> oldBoard = (List<List<SudokuField>>) evt.getOldValue();

        // jeśli oldBoard ma wartość null to znaczy że to jest pierwsza zmiana na desce i trzeba ją wyzerować
        if (oldBoard.get(0).get(0) == null) {
            for (int i = 0; i < 9; i++) {
                oldBoard.set(i, Arrays.asList(new SudokuField[9]));
            }
            for (int y = 0; y < 9; y++) {
                for (int x = 0; x < 9; x++) {
                    //oldBoard[y][x] = new SudokuField(0);
                    oldBoard.get(y).set(x, new SudokuField(0));
                }
            }
        }

        //SudokuField[][] newBoard = (SudokuField[][]) evt.getNewValue();
        List<List<SudokuField>> newBoard = (List<List<SudokuField>>) evt.getNewValue();

        // wypisujemy każdy SudokuField który się zmienił
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (oldBoard.get(y).get(x).getFieldValue() != newBoard.get(y).get(x).getFieldValue()) {
                    System.out.println("SudokuField[" + y + "][" + x + "] changed from "
                            + oldBoard.get(y).get(x).getFieldValue() + " to " + newBoard.get(y).get(x).getFieldValue());
                }
            }
        }

        // sprawzamy czy nowa deska jest poprawna
        // w obecnej wersji nie ma możliwości żeby była niepoprawna, bo wywołujemy setter tylko w
        // backtrackingu, a tam sprawdzamy czy jest poprawna przed wywołaniem setter
        SudokuBoard sudokuBoard = (SudokuBoard) evt.getSource();
        // Rows
        for (int y = 0; y < 9; y++) {
            if (!sudokuBoard.getRow(y).verify()) {
                System.out.println("Row " + y + " is not valid");
            }
        }
        // Columns
        for (int x = 0; x < 9; x++) {
            if (!sudokuBoard.getColumn(x).verify()) {
                System.out.println("Column " + x + " is not valid");
            }
        }
        // Boxes
        for (int y = 0; y < 9; y += 3) {
            for (int x = 0; x < 9; x += 3) {
                if (!sudokuBoard.getBox(x, y).verify()) {
                    System.out.println("Box " + x + " " + y + " is not valid");
                }
            }
        }
    }
}
