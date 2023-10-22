package org.example;

import java.util.ArrayList;
import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver {

    SudokuBoard board;

    public BacktrackingSudokuSolver() {

    }

    @Override
    public boolean solve(SudokuBoard board) {
        this.board = board;
        return backtrackingSolve(0, 0);
    }

    //Funkcja rekurencyjna generująca poprawną tablicę sudoku. Wykorzystuje algorytm typu "backtracking"
    private boolean backtrackingSolve(int x, int y) {
        if (x == 9) {
            return true;
        } else if (y == 9) {
            return backtrackingSolve(x + 1, 0);
        } else if (board.get(x, y) != 0) {
            return backtrackingSolve(x, y + 1);
        } else {
            ArrayList<Integer> pastNumbers = new ArrayList<>();
            int i = 0;
            while (i < 9) {
                int num = generateRandomNumber();
                while (pastNumbers.contains(num)) {
                    num = generateRandomNumber();
                }
                pastNumbers.add(num);
                if (board.checkBoard(num, x, y)) {
                    board.set(x, y, num);
                    if (backtrackingSolve(x, y + 1)) {
                        return true;
                    }
                    board.set(x, y, 0);
                }
                i++;
            }
            return false;
        }
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(1, 10);
    }
}
