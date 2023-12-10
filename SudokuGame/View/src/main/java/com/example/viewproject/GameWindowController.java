package com.example.viewproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.example.BacktrackingSudokuSolver;
import org.example.Difficulty;
import org.example.SudokuBoard;
import org.example.SudokuSolver;

public class GameWindowController {
    @FXML
    public Label difficultyText;

    private Difficulty difficulty;

    public GridPane sudokuBoardGrid;

    BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    SudokuBoard board = new SudokuBoard(solver);

    public void initialize() {}
    public void initData(Difficulty difficulty) {
        this.difficulty = difficulty;
        difficultyText.setText(difficulty.name());

        sudokuBoardGrid.setAlignment(Pos.CENTER);

        drawSudoku();
    }

    public void drawSudoku() {
        board.solveGame();
        board.clearRandomFields(difficulty);

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                TextField textField = new TextField();
                textField.setMaxWidth(50);
                textField.setMinHeight(50);
                textField.setMaxHeight(50);
                textField.setAlignment(Pos.CENTER);
                //textField.setStyle("-fx-text-fill: black; -fx-font-size: 16px;");
                int boardVal = board.get(x, y);
                String textFillColor = "black";
                if (boardVal != 0){
                    textField.setText(String.valueOf(boardVal));
                    textField.setDisable(true);
                    textFillColor = "gray";
                } else {
                    textField.setText("");
                }
                String borderStyle = "hidden ";

                if ((x + 1) % 3 == 0 && x + 1 != 9) {
                    borderStyle += "solid ";
                } else {
                    borderStyle += "hidden ";
                }

                if ((y + 1) % 3 == 0 && y + 1 != 9) {
                    borderStyle += "solid ";
                } else {
                    borderStyle += "hidden ";
                }

                borderStyle += "hidden";

                textField.setStyle(String.format("-fx-text-fill: %s; -fx-font-size: 16px; -fx-border-style: %s; -fx-border-width: 2; -fx-border-color: #000000; -fx-opacity: 1.0;", textFillColor, borderStyle));

                textField.setOnKeyTyped(actionEvent -> {
                    String value = actionEvent.getCharacter();
                    textField.setText("");
                    if (StringUtils.isNumeric(value)) {
                        if (value.length() > 1) {
                            textField.setText(value.substring(0, 1));
                            return;
                        }
                        if (Integer.parseInt(value) > 9) {
                            textField.setText("9");
                            return;
                        }
                        if (Integer.parseInt(value) < 1) {
                            textField.setText("1");
                            return;
                        }
                        textField.setText(value);
                    } else {
                        textField.setText("");
                    }
                });

                sudokuBoardGrid.add(textField, x, y);
            }
        }
    }

}
