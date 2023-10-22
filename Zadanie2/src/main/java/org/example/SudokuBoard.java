package org.example;

import java.util.ArrayList;
import java.util.Random;

public class SudokuBoard {

    //Deklaracja zmiennej board o wymiarach 9 x 9
    private int[][] board = new int[9][9];

    //konstruktor klasy
    public SudokuBoard() {

    }

    //Funkcja wypełniająca tablicę sudoku za pomocą funkcji rekurencyjnej backtrackingFill
    public void fillBoard() {
        // clean board
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                setNumber(x, y, 0);
            }
        }

        backtrackingFill(0, 0);
    }

    //Funkcja rekurencyjna generująca poprawną tablicę sudoku. Wykorzystuje algorytm typu "backtracking
    // ŻRÓDŁA: https://iopscience.iop.org/article/10.1088/1742-6596/1566/1/012038
    //         https://web.archive.org/web/20070317015632/http://www.cse.ohio-state.edu/~gurari/course/cis680/cis680Ch19.html#QQ1-51-128
    private boolean backtrackingFill(int x, int y) {
        if (x == 9) {
            return true;
        } else if (y == 9) {
            return backtrackingFill(x + 1, 0);
        } else {
            ArrayList<Integer> pastNumbers = new ArrayList<>();
            int i = 0;
            while (i < 9) {
                int num = generateRandomNumber();
                while (pastNumbers.contains(num)) {
                    num = generateRandomNumber();
                }
                pastNumbers.add(num);
                if (checkBoard(num, x, y)) {
                    setNumber(x, y, num);
                    if (backtrackingFill(x, y + 1)) {
                        return true;
                    }
                    setNumber(x, y, 0);
                }
                i++;
            }
            return false;
        }
    }

    //Funkcja wypisująca całą tablicę sudoku w formacie 9 x 9
    public void printBoard() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.print(" " + getNumber(x, y) + " ");
            }
            System.out.print("\n");
        }
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
    public int getNumber(int x, int y) {
        return board[x][y];
    }

    //Funkcja ustawiająca liczbę na danych koordynatach
    private void setNumber(int x, int y, int num) {
        board[x][y] = num;
    }

    //Funkcja sprawdzająca całą tablicę, czy jest ona poprawna wg zasad sudoku
    private boolean checkBoard(int num, int x, int y) {
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
    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(1, 10);
    }

    //Funkcja sprawdzająca danych rząd wg zasad sudoku
    private boolean checkRow(int num, int x) {
        for (int y = 0; y < 9; y++) {
            if (getNumber(x, y) == num) {
                return false;
            }
        }
        return true;
    }

    //FUnkcja sprawdzająca daną kolumnę wg zasad sudoku
    private boolean checkColumn(int num, int y) {
        for (int x = 0; x < 9; x++) {
            if (getNumber(x, y) == num) {
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
                if (getNumber(i, j) == num) {
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
