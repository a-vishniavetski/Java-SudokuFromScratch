package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class BacktrackingSudokuSolver implements SudokuSolver, Serializable, Cloneable {

    private SudokuBoard board;

    public BacktrackingSudokuSolver() {

    }

    @Override
    public BacktrackingSudokuSolver clone() throws CloneNotSupportedException {
        return (BacktrackingSudokuSolver) super.clone();
    }

    @Override
    public void solve(SudokuBoard board) {
        this.board = board;
        backtrackingSolve(0, 0);
    }

    //Funkcja rekurencyjna generująca poprawną tablicę sudoku. Wykorzystuje algorytm typu "backtracking"
    // ŻRÓDŁA: https://iopscience.iop.org/article/10.1088/1742-6596/1566/1/012038
    //         https://web.archive.org/web/20070317015632/http://www.cse.ohio-state.edu/~gurari/course/cis680/cis680Ch19.html#QQ1-51-128
    private boolean backtrackingSolve(int x, int y) {
        if (x == 9) {
            return true;
        } else if (y == 9) {
            return backtrackingSolve(x + 1, 0);
        //        } else if (board.get(x, y) != 0) {
        //            return backtrackingSolve(x, y + 1);
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
