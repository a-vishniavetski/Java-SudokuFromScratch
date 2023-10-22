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
        board.fillBoard();
        int[][] board_arr = board.getBoard();

        // ----- WIERZE -----
        for (int y = 0; y < 9; y++){
            Set<Integer> number_set = new HashSet<>();
            ArrayList<Integer> number_list = new ArrayList<>();

            for (int x = 0; x < 9; x++) {
                number_set.add(board_arr[x][y]);
                number_list.add(board_arr[x][y]);
            }

            // jak liczby się nie powtarzają to rozmiar seta będzie równy rozmiarowi listy
            assertTrue(number_set.size() == number_list.size());
        }

        // ----- KOLUMNY -----
        for (int x = 0; x < 9; x++){
            Set<Integer> number_set = new HashSet<>();
            ArrayList<Integer> number_list = new ArrayList<>();

            for (int y = 0; y < 9; y++) {
                number_set.add(board_arr[x][y]);
                number_list.add(board_arr[x][y]);
            }

            // jak liczby się nie powtarzają to rozmiar seta będzie równy rozmiarowi listy
            assertTrue(number_set.size() == number_list.size());
        }

        // ----- KWADRATY 3x3 -----
        for (int x = 0; x < 9; x += 3) {
            for (int y = 0; y < 9; y += 3) {
                Set<Integer> number_set = new HashSet<>();
                ArrayList<Integer> number_list = new ArrayList<>();

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        number_set.add(board_arr[x + i][y + j]);
                        number_list.add(board_arr[x + i][y + j]);
                    }
                }

                // jak liczby się nie powtarzają to rozmiar seta będzie równy rozmiarowi listy
                assertTrue(number_set.size() == number_list.size());
            }
        }
    }

    // testuje czy FillBoard generuje rózne tablice w tej samej instancji klasy przy
    // kolejnych wywołaniach
    @Test
    public void DifferenceFillBoardTest(){
        SudokuBoard board = new SudokuBoard();

        int[][] board1 = new int[9][9];
        int[][] board2 = new int[9][9];

        board.fillBoard();
        board1 = board.getBoard();

        board.fillBoard();
        board2 = board.getBoard();

        // sprawdzamy czy tablice są równe
        boolean has_different_elements = false;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board1[i][j] != board2[i][j]){
                    has_different_elements = true;
                    break;
                }
            }
        }
        assertTrue(has_different_elements);

    }
}
