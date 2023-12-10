package com.example.viewproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Difficulty;

import java.io.IOException;
import java.util.Objects;

public class NewGameController {
    @FXML
    public Label difficultyText;

    @FXML
    public ToggleGroup difficultyToggleGroup;
    public Button newGameBtn;
    public VBox newGameVbox;
    public VBox radiosVbox;

    public void init() {
        for (Difficulty difficulty : Difficulty.values()) {
            RadioButton r = new RadioButton();
            r.setText(difficulty.toString());
            r.setId(difficulty.name());
            r.setToggleGroup(difficultyToggleGroup);
            if (difficulty.name().equals(Difficulty.values()[0].name())) {
                r.setSelected(true);
            }
            radiosVbox.getChildren().add(r);
        }
    }

    @FXML
    public void onStartGameBtnClick() {
        RadioButton radioButton = (RadioButton) difficultyToggleGroup.getSelectedToggle();
        System.out.println(radioButton.getId());
        Difficulty difficulty = Difficulty.valueOf(radioButton.getId());
        openGameWindow(difficulty);
    }

    public void openGameWindow(Difficulty diff) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("game-window.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Gra Sudoku - " + diff.toString());
            Scene scene = new Scene(loader.load(), 600, 500);
            stage.setScene(scene);
            GameWindowController controller = loader.getController();
            controller.initData(diff);
            stage.setResizable(false);
            stage.show();

            Stage newGameWindow = (Stage) newGameBtn.getScene().getWindow();
            newGameWindow.hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
