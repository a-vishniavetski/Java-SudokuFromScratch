package org.example;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SudokuBoard implements Serializable {

    //Deklaracja zmiennej board o wymiarach 9 x 9
    //Zrodlo fixed listy
    //https://docs.oracle.com/javase/8/docs/api/java/util/Arrays.html#asList-T...-
    //private SudokuField[][] board = new SudokuField[9][9];
    private List<List<SudokuField>> board = Arrays.asList(new List[9]);

    private BacktrackingSudokuSolver sudokuSolver;

    //konstruktor klasy
    public SudokuBoard(BacktrackingSudokuSolver sudokuSolver) {

        for (int i = 0; i < 9; i++) {
            board.set(i, Arrays.asList(new SudokuField[9]));
        }

        // zerujemy tablicę
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //board[i][j] = new SudokuField(0);
                board.get(i).set(j, new SudokuField(0));
            }
        }

        this.sudokuSolver = sudokuSolver;
    }

    public void solveGame() {
        // oczyszcamy tablicę
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //board[i][j] = new SudokuField(0);
                board.get(i).set(j, new SudokuField(0));
            }
        }

        sudokuSolver.solve(this);
    }

    //Funkcja zwracająca liczbę z danych koordynatów x y
    public int get(int x, int y) {
        //return board[x][y].getFieldValue();
        return board.get(x).get(y).getFieldValue();
    }

    //Funkcja ustawiająca liczbę na danych koordynatach
    public void set(int x, int y, int num) {
        // zapisujemy tablice przed zmianą aby zaktualizować listenerów
        //SudokuField[][] oldBoard = new SudokuField[9][9];
        List<List<SudokuField>> oldBoard = Arrays.asList(new List[9]);
        for (int i = 0; i < 9; i++) {
            oldBoard.set(i, Arrays.asList(new SudokuField[9]));
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //oldBoard[i][j] = new SudokuField(board[i][j].getFieldValue());
                //oldBoard[i][j] = new SudokuField(board.get(i).get(j).getFieldValue());
                oldBoard.get(i).set(j, new SudokuField(board.get(i).get(j).getFieldValue()));
            }
        }

        //board[x][y].setFieldValue(num);
        board.get(x).get(y).setFieldValue(num);

        // zaktualiujemy listenerów
        //notifyListeners(oldBoard);
        notifyListeners(oldBoard);
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
        //SudokuField[] array = new SudokuField[9];
        List<SudokuField> array = Arrays.asList(new SudokuField[9]);
        int k = 0;
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                //array[k] = board[i][j];
                array.set(k, board.get(i).get(j));
                k++;
            }
        }
        return new SudokuBox(array);
    }

    public SudokuRow getRow(int y) {
        //SudokuField[] array = board[y];
        List<SudokuField> array = board.get(y);
        return new SudokuRow(array);
    }

    public SudokuColumn getColumn(int x) {
        //SudokuField[] array = new SudokuField[9];
        List<SudokuField> array = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            //array[i] = board[i][x];
            array.set(i, board.get(i).get(x));
        }
        return new SudokuColumn(array);
    }

    // pomaga korzystać z wzorca projektowego Listener zaimplementowanego przez JavaBean
    public PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    private void notifyListeners(List<List<SudokuField>> oldBoard) {
        propertyChangeSupport.firePropertyChange("board", oldBoard, board);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SudokuBoard = {\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                builder.append(board.get(i).get(j).toString());
                if (j != 8) {
                    builder.append(", ");
                }
            }
            builder.append("\n");
        }
        builder.append("}");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(board);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SudokuBoard brd = (SudokuBoard) obj;

        // sprawdzamy czy wszystkie elementy są takie same
        for (int i = 0; i < 9; i++) {
            if (!board.get(i).equals(brd.board.get(i))) {
                return false;
            }
        }

        return true;
    }

    //testowe funkcje
    /*
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" " + board.get(i).get(j).getFieldValue() + " ");
            }
            System.out.println();
        }
    }

    public void printRow(int y) {
        SudokuRow row = getRow(y);
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + row.array.get(i).getFieldValue() + " ");
        }
    }

    public void printColumn(int x) {
        SudokuColumn column = getColumn(x);
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + column.array.get(i).getFieldValue() + " ");
        }
    }

    public void printBox(int x, int y) {
        SudokuBox box = getBox(x, y);
        int k = 1;
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + box.array.get(i).getFieldValue() + " ");
            if (k % 3 == 0) {
                System.out.println();
            }
            k++;
        }
    }*/

}
