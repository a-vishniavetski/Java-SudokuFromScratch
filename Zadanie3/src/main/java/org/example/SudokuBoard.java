package org.example;

public class SudokuBoard {

    //Deklaracja zmiennej board o wymiarach 9 x 9
    private int[][] board = new int[9][9];
    private BacktrackingSudokuSolver sudokuSolver;

    //konstruktor klasy
    public SudokuBoard(BacktrackingSudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;
    }

    public void solveGame() {
        // oczyszcamy tablicę
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 0;
            }
        }

        sudokuSolver.solve(this);
    }

    //Funkcja zwracająca kopię tablicy sudoku, ale w formie, w której niemożliwe jest
    //modyfikowanie jej z zewnątrz
    public int[][] getBoard() {
        int[][] tab = new int[9][9];
        for (int x = 0; x < 9; x++) {
            System.arraycopy(board[x], 0, tab[x], 0, 9);
        }
        return tab;
    }

    //Funkcja zwracająca liczbę z danych koordynatów x y
    public int get(int x, int y) {
        return board[x][y];
    }

    //Funkcja ustawiająca liczbę na danych koordynatach
    public void set(int x, int y, int num) {
        board[x][y] = num;
    }



    //Funkcja sprawdzająca całą tablicę, czy jest ona poprawna wg zasad sudoku
    boolean checkBoard(int num, int x, int y) {
        if (!checkRow(num, x)) {
            return false;
        }
        if (!checkColumn(num, y)) {
            return false;
        }
        if (!check3by3(num, x, y)) {
            return false;
        }
        return true;
    }

    //Funkcja generująca losową cyfrę od 1 do 9

    //Funkcja sprawdzająca danych rząd wg zasad sudoku
    private boolean checkRow(int num, int x) {
        for (int y = 0; y < 9; y++) {
            if (get(x, y) == num) {
                return false;
            }
        }
        return true;
    }

    //FUnkcja sprawdzająca daną kolumnę wg zasad sudoku
    private boolean checkColumn(int num, int y) {
        for (int x = 0; x < 9; x++) {
            if (get(x, y) == num) {
                return false;
            }
        }
        return true;
    }

    //Funkcja sprawdzająca dany kwadrat wg zasad sudoku
    private boolean check3by3(int num, int x, int y) {
        x = getXOfSquare(x);
        y = getYOfSquare(y);
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (get(i, j) == num) {
                    return false;
                }
            }
        }
        return true;
    }

    //Funkcja znajdująca pierwszy rząd (lewy górny róg) kwadratu po podaniu danej x
    private int getXOfSquare(int x) {
        return x - (x % 3);
    }

    //Funkcja znajdująca pierwszą kolumnę (lewy górny róg) kwadratu po podaniu danej y
    private int getYOfSquare(int y) {
        return y - (y % 3);
    }
}
