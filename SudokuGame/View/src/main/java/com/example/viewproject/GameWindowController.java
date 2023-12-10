package com.example.viewproject;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.BacktrackingSudokuSolver;
import org.example.Difficulty;
import org.example.SudokuBoard;
import org.example.SudokuSolver;

public class GameWindowController {
    @FXML
    public Label difficultyText;

    BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    SudokuBoard board = new SudokuBoard(solver);

    public void initialize() {}
    public void initData(Difficulty difficulty) {
        difficultyText.setText(difficulty.name());
        board.solveGame();
        board.clearRandomFields(difficulty);

        GridPane gridPane = new GridPane();
    }
}
