package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(backtrackingSudokuSolver);
        board.solveGame();
        board.printBoard();

        System.out.println();
        System.out.println();
        board.printRow(0);
        System.out.println();
        System.out.println();
        board.printColumn(0);
        System.out.println();
        System.out.println();
        board.printBox(3, 4);
    }
}