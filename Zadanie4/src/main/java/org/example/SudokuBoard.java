package org.example;

public class SudokuBoard {

    //Deklaracja zmiennej board o wymiarach 9 x 9
    private SudokuField[][] board = new SudokuField[9][9];
    private BacktrackingSudokuSolver sudokuSolver;

    //konstruktor klasy
    public SudokuBoard(BacktrackingSudokuSolver sudokuSolver) {
        this.sudokuSolver = sudokuSolver;
    }

    public void solveGame() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField(0);
            }
        }

        // oczyszcamy tablicę
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                board[i][j].setFieldValue(0);
//            }
//        }

        sudokuSolver.solve(this);
    }

    //Funkcja zwracająca liczbę z danych koordynatów x y
    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    //Funkcja ustawiająca liczbę na danych koordynatach
    public void set(int x, int y, int num) {
        board[x][y].setFieldValue(num);
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

    public SudokuBox getBox(int x, int y) {
        x = getXOfSquare(x);
        y = getYOfSquare(y);
        SudokuField[] array = new SudokuField[9];
        int k = 0;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                array[k] = board[i][j];
                k++;
            }
        }
        return new SudokuBox(array);
    }

    public SudokuRow getRow(int y) {
        SudokuField[] array = board[y];
        return new SudokuRow(array);
    }

    public SudokuColumn getColumn(int x) {
        SudokuField[] array = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            array[i] = board[i][x];
        }
        return new SudokuColumn(array);
    }

    //testowe funkcje
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" " + board[i][j].getFieldValue() + " ");
            }
            System.out.println();
        }
    }

    public void printRow(int y) {
        SudokuRow row = getRow(y);
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + row.array[i].getFieldValue() + " ");
        }
    }

    public void printColumn(int x) {
        SudokuColumn column = getColumn(x);
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + column.array[i].getFieldValue() + " ");
        }
    }

    public void printBox(int x, int y) {
        SudokuBox box = getBox(x, y);
        int k = 1;
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + box.array[i].getFieldValue() + " ");
            if (k % 3 == 0) {
                System.out.println();
            }
            k++;
        }
    }
}
