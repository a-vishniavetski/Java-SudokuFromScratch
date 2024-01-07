package com.example.viewproject;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.apache.commons.lang3.StringUtils;
import org.example.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class GameWindowController {
    @FXML
    public Label difficultyText;
    @FXML
    public Button saveBoardBtn;
    @FXML
    public Button loadBoardBtn;

    private Difficulty difficulty;

    public GridPane sudokuBoardGrid;

    public Locale currentLocale = Locale.getDefault();

    BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();

    SudokuBoard primalBoard;

    SudokuBoard board = new SudokuBoard(solver);

    // Logging
    private static final Logger Logger = java.util.logging.Logger.getLogger(GameWindowController.class.getName());

    public void initialize() {
        saveBoardBtn.setOnMouseClicked(mouseEvent -> {
            try {
                saveBoard();
            } catch (SudokuWriteException e) {
                Logger.info(e.getMessage());
            }
        });
        loadBoardBtn.setOnMouseClicked(mouseEvent -> {
            try {
                loadBoard();
            } catch (SudokuReadException e) {
                Logger.info(e.getMessage());
            }
        });
    }
    public void initData(Difficulty difficulty) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.example.viewproject.lang", currentLocale);
        this.difficulty = difficulty;
        String localizedDifficulty = bundle.getString(difficulty.name());
        String localizedSaveBtn = bundle.getString("saveBtnText");
        String localizedLoadBtn = bundle.getString("loadBtnText");

        saveBoardBtn.setText(localizedSaveBtn);
        loadBoardBtn.setText(localizedLoadBtn);

        difficultyText.setText(localizedDifficulty);

        sudokuBoardGrid.setAlignment(Pos.CENTER);

        drawSudoku();
    }

    public void drawSudoku() {
        board.solveGame();
        board.clearRandomFields(difficulty);
        try {
            primalBoard = board.clone();
        } catch (CloneNotSupportedException e) {
            try {
                throw new SudokuCloneException("CloneError", e);
            } catch (SudokuCloneException ex) {
                Logger.info(ex.getMessage());
                exit(1);
            }
        }

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                TextField textField = new TextField();
                textField.setMaxWidth(50);
                textField.setMinHeight(50);
                textField.setMaxHeight(50);
                textField.setAlignment(Pos.CENTER);
                textField.setId(String.valueOf(x) + "_" + String.valueOf(y));
                setField(textField, x, y);

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
                        int X = Integer.parseInt(textField.getId().substring(0, 1));
                        int Y = Integer.parseInt(textField.getId().substring(textField.getId().length() - 1));
                        board.set(X, Y, Integer.parseInt(value));
                    } else {
                        textField.setText("");
                    }
                });
                sudokuBoardGrid.add(textField, x, y);

            }
        }
    }

    public void saveBoard() throws SudokuWriteException {
        try (FileSudokuBoardDao dao = new FileSudokuBoardDao("saved_board")){
            dao.writeWithPrimal(primalBoard, board);
        } catch (Exception e) {
            throw new SudokuWriteException("WriteError", e);
        }
    }

    public void loadBoard() throws SudokuReadException {
        try (FileSudokuBoardDao dao = new FileSudokuBoardDao("saved_board")){
            ArrayList<SudokuBoard> boards = new ArrayList<>();
            boards = dao.readWithPrimal();
            primalBoard = boards.get(0);
            board = boards.get(1);
            updateAllFields();
        } catch (Exception e) {
            throw new SudokuReadException("ReadError", e);
        }
    }

    private void updateAllFields() {
        Set<Node> fields = sudokuBoardGrid.lookupAll("TextField");
        int x = 0;
        int y = 0;
        for (Node node : fields) {
            if (node instanceof TextField field) {
                field.getStyleClass().clear();
                field.getStyleClass().addAll("text-field", "text-input");

                setField(field, x, y);

                if (y < 8) {
                    y++;
                } else {
                    x++;
                    y = 0;
                }
            }
        }
    }

    private void setField(TextField field, int x, int y) {
        int boardVal = board.get(x, y);
        String textFillColor;
        if (boardVal != 0){
            field.setText(String.valueOf(boardVal));
            if (primalBoard.get(x, y) == 0) {
                field.setDisable(false);
                textFillColor = "black";
            } else {
                field.setDisable(true);
                textFillColor = "gray";
            }

        } else {
            field.setText("");
            field.setDisable(false);
            textFillColor = "black";
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

        field.setStyle(String.format("-fx-text-fill: %s; -fx-font-size: 16px; -fx-border-style: %s; -fx-border-width: 2; -fx-border-color: #000000; -fx-opacity: 1.0;", textFillColor, borderStyle));
    }
}