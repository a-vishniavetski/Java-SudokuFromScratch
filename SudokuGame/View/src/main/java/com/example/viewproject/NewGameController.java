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
import javafx.stage.Stage;
import org.example.Difficulty;

import java.io.IOException;
import java.util.Objects;

public class NewGameController {
    @FXML
    public Label difficultyText;
    public Label chosenDifficultyText;

    @FXML
    public ToggleGroup difficultyToggleGroup;
    public Button newGameBtn;

    @FXML
    public void onStartGameBtnClick() {
        RadioButton radioButton = (RadioButton) difficultyToggleGroup.getSelectedToggle();
        System.out.println(radioButton.getId());
        Difficulty diff = switch (radioButton.getId()) {
            case "easy" -> Difficulty.EASY;
            case "normal" -> Difficulty.NORMAL;
            case "hard" -> Difficulty.HARD;
            default -> Difficulty.NORMAL;
        };
        openGameWindow(diff);
    }

    @FXML
    public void onSelectDifficulty() {
        RadioButton radioButton = (RadioButton) difficultyToggleGroup.getSelectedToggle();
//        chosenDifficultyText.setText("Wybrany poziom trudności: " + radioButton.getText());
    }

    public void openGameWindow(Difficulty diff) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("game-window.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            Scene scene = new Scene(loader.load(), 600, 500);
            stage.setScene(scene);
            GameWindowController controller = loader.getController();
            controller.initData(diff);
            stage.show();

            Stage newGameWindow = (Stage) newGameBtn.getScene().getWindow();
            newGameWindow.hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
