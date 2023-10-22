package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        SudokuBoard board = new SudokuBoard();
        board.solveGame();
        board.printBoard();
    }
}