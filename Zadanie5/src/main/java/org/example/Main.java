package org.example;

public class Main {
    public static void main(String[] args) {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(backtrackingSudokuSolver);

        SudokuBoardChangeListener listener = new SudokuBoardChangeListener();
        board.addPropertyChangeListener(listener);

        board.solveGame();
        //board.printBoard();

        //List<List<Integer>> lista = Arrays.asList(Arrays.asList(new Integer[9]));
        //lista.get(0).set(0, 1);
        /*
        board.printBoard();

        System.out.println();
        board.printRow(0);
        System.out.println();
        System.out.println(board.getRow(0).verify());
        System.out.println();
        board.printColumn(0);
        System.out.println();
        System.out.println(board.getColumn(0).verify());
        System.out.println();
        board.printBox(3, 4);
        System.out.println(board.getBox(3, 4).verify());*/
    }
}