package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class SudokuBoardTest {

    // testuje czy wygenerowana przez FillBoard tablica jest poprawna
    @Test
    public void SolveGameTest(){
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);
        board.solveGame();
        int[][] board_arr = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9 ; j++) {
                board_arr[i][j] = board.get(i, j);
            }
        }

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

    // testuje czy FillBoard generuje rózne tablice przy kolejnych wywołaniach
    @Test
    public void DifferenceSolveGameTest(){
        BacktrackingSudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(sudokuSolver);

        int[][] board1 = new int[9][9];
        int[][] board2 = new int[9][9];

        board.solveGame();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9 ; j++) {
                board1[i][j] = board.get(i, j);
            }
        }

        board.solveGame();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board2[i][j] = board.get(i, j);
            }
        }

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
