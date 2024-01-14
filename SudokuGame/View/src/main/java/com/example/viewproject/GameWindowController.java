package com.example.viewproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import org.apache.commons.lang3.StringUtils;
import org.example.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class GameWindowController {
    @FXML
    public Label difficultyText;
    @FXML
    public Button saveBoardBtn;
    @FXML
    public Button loadBoardBtn;
    @FXML
    public Button checkBtn;

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
            // create a text input dialog
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");
            LocalDateTime now = LocalDateTime.now();
            // TODO: Lokalizacja tekstu tutaj
            TextInputDialog td = new TextInputDialog("sudoku_" + dtf.format(now));
            // setHeaderText
            td.setHeaderText("Wpisz nazwę planszy");

            Optional<String> result = td.showAndWait();
            if (result.isPresent()) {
                System.out.println(td.getEditor().getText());
                try {
                    saveBoard(td.getEditor().getText());
                } catch (SudokuWriteException e) {
                    Logger.info(e.getMessage());
                }
            }

        });
        loadBoardBtn.setOnMouseClicked(mouseEvent -> {
            ArrayList<String> names = new ArrayList<>();
            try (JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao("test")) {
                names = dao.getAllBoardNames();
            } catch (Exception e ){
                System.out.println(e.getMessage());
            }

            ListView<String> list = new ListView<String>();
            ObservableList<String> items = FXCollections.observableArrayList (
                    names);
            list.setItems(items);

            Dialog<String> dialog = new Dialog<>();
            GridPane gridPane = new GridPane();
            gridPane.add(list, 0, 0);
            dialog.getDialogPane().setContent(gridPane);
            // TODO: Lokalizacja nazwy przycisków w popupach
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE));
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Wczytaj", ButtonBar.ButtonData.OK_DONE));
            Button closeButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.CLOSE);


            Optional<String> result = dialog.showAndWait();
            String boardName = "";
            if (result.isPresent()) {
                if (result.toString().contains("buttonData=CANCEL")) {
                    System.out.println("closed");
                    return;
                }
                boardName = list.getSelectionModel().getSelectedItem();
            }

            try {
                loadBoardFromDB(boardName);
            } catch (SudokuReadException e) {
                Logger.info(e.getMessage());
            }
        });
        checkBtn.setOnMouseClicked(mouseEvent -> {
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    if (!board.getRow(y).verify() && !board.getColumn(x).verify()) {
                        System.out.println("Something is wrong");
                        return;
                    }
                }
            }
            for (int x = 0; x < 9; x += 3){
                for (int y = 0; y < 9; y += 3) {
                    if (!board.getBox(x, y).verify()) {
                        System.out.println("something is wrong");
                        return;
                    }
                }
            }
            System.out.println("You win!");
        });
    }
    public void initData(Difficulty difficulty) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.example.viewproject.lang", currentLocale);
        this.difficulty = difficulty;
        String localizedDifficulty = bundle.getString(difficulty.name());
        String localizedSaveBtn = bundle.getString("saveBtnText");
        String localizedLoadBtn = bundle.getString("loadBtnText");
        String localizedCheckBtn = bundle.getString("checkBtn");

        saveBoardBtn.setText(localizedSaveBtn);
        loadBoardBtn.setText(localizedLoadBtn);
        checkBtn.setText(localizedCheckBtn);

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

    public void saveBoard(String boardName) throws SudokuWriteException {
        try (FileSudokuBoardDao dao = new FileSudokuBoardDao(boardName);
                JdbcSudokuBoardDao daoDB = new JdbcSudokuBoardDao(boardName)){
            dao.write(primalBoard, board);
            daoDB.write(board);
        } catch (Exception e) {
            throw new SudokuWriteException("WriteError", e);
        }
        try (JdbcSudokuBoardDao daoDB = new JdbcSudokuBoardDao(boardName + "_primal")){
            daoDB.write(primalBoard);
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

    public void loadBoardFromDB(String boardName) throws SudokuReadException {
        try (JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao(boardName)){
            board = dao.read();
        } catch (Exception e) {
            throw new SudokuReadException("ReadError", e);
        }
        updateAllFields();

        try (JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao(boardName + "_primal")){
            primalBoard = dao.read();
        } catch (Exception e) {
            throw new SudokuReadException("ReadError", e);
        }
        System.out.println(board);
        System.out.println(primalBoard);
        updateAllFields();
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