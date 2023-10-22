package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class SudokuBoardTest {

    // testuje czy wygenerowana przez FillBoard tablica jest poprawna
    @Test
    public void FillBoardTest(){
        SudokuBoard board = new SudokuBoard();
        board.solveGame();

        // testujemy czy wiersze nie zawierają powtarzających się liczb
        for (int x = 0; x < 9; x++) {
            assertTrue(checkRow(board, x));
        }

        // testujemy czy kolumne nie zawierają powtarzających się liczb
        for (int y = 0; y < 9; y++) {
            assertTrue(checkColumn(board, y));
        }

        // testujemy czy w każdym kwadracie 3x3 nie ma powtarzających się liczb
        for (int x = 0; x < 9; x += 3) {
            for (int y = 0; y < 9; y += 3) {
                assertTrue(checkSquare(board, x, y));
            }
        }
    }

    // testuje czy FillBoard generuje rózne tablice w różnych instancjach klasy
    @Test
    public void DifferentBoardsFillBoardTest(){
        SudokuBoard board1 = new SudokuBoard();
        SudokuBoard board2 = new SudokuBoard();

        board1.solveGame();
        board2.solveGame();


        boolean arr_equal = board1.getBoard().equals(board2.getBoard());
        assertTrue(!arr_equal);
    }

    // testuje czy FillBoard generuje rózne tablice w tej samej instancji klasy przy
    // kolejnych wywołaniach
    @Test
    public void SameBoardFillBoardTest(){
        SudokuBoard board = new SudokuBoard();

        int[][] board1 = new int[9][9];
        int[][] board2 = new int[9][9];


        board.solveGame();
        board1 = board.getBoard().clone();

        board.solveGame();
        board2 = board.getBoard().clone();

        boolean arr_equal = board1.equals(board2);
        assertFalse(arr_equal);
    }

    // sprawdza czy wiersze nie zawierają powtarzających się liczb
    private boolean checkRow(SudokuBoard board, int y) {
        Set<Integer> number_set = new HashSet<>();
        ArrayList<Integer>  number_list = new ArrayList<>();

        for (int x = 0; x < 9; x++) {
            number_set.add(board.get(x, y));
            number_list.add(board.get(x, y));
        }

        // jak liczby się nie powtarzają to rozmiar seta będzie równy rozmiarowi listy
        if(number_set.size() == number_list.size()){
            return true;
        }
        else{
            return false;
        }
    }

    // sprawdza czy kolumny nie zawierają powtarzających się liczb
    private boolean checkColumn(SudokuBoard board, int x){
        Set<Integer> number_set = new HashSet<>();
        ArrayList<Integer>  number_list = new ArrayList<>();

        for (int y = 0; y < 9; y++) {
            number_set.add(board.get(x, y));
            number_list.add(board.get(x, y));
        }

        // jak liczby się nie powtarzają to rozmiar seta będzie równy rozmiarowi listy
        if(number_set.size() == number_list.size()){
            return true;
        }
        else{
            return false;
        }
    }

    // sprawdza czy kwadraty 3x3 nie zawierają powtarzających się liczb
    private boolean checkSquare(SudokuBoard board, int x, int y){
        Set<Integer> number_set = new HashSet<>();
        ArrayList<Integer>  number_list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                number_set.add(board.get(x + i, y + j));
                number_list.add(board.get(x + i, y + j));
            }
        }

        // jak liczby się nie powtarzają to rozmiar seta będzie równy rozmiarowi listy
        if(number_set.size() == number_list.size()){
            return true;
        }
        else{
            return false;
        }
    }
}
