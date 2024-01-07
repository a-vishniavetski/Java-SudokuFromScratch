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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Difficulty;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewGameController {
    @FXML
    public Label difficultyText;

    @FXML
    public ToggleGroup difficultyToggleGroup;
    public ToggleGroup langToggleGroup;
    @FXML
    public Button newGameBtn;
    @FXML
    public VBox newGameVbox;
    @FXML
    public VBox radiosVbox;

    public Locale currentLocale = Locale.getDefault();
    @FXML
    public VBox langRadiosVbox;
    @FXML
    public HBox langRadiosHbox;

    @FXML
    public RadioButton EASY;
    @FXML
    public RadioButton NORMAL;
    @FXML
    public RadioButton HARD;
    @FXML
    public Label welcomeText;

    @FXML
    public Label authorsText;

    Stage primaryStage;

    public void init(Stage primaryStage) {
        ResourceBundle bundle = ResourceBundle.getBundle("com.example.viewproject.lang", currentLocale);
        this.primaryStage = primaryStage;
        RadioButton pl_PL = new RadioButton();
        RadioButton en_EN = new RadioButton();

        pl_PL.setToggleGroup(langToggleGroup);
        en_EN.setToggleGroup(langToggleGroup);

        pl_PL.setText("Polski");
        en_EN.setText("English");
        pl_PL.setId("pl_PL");
        en_EN.setId("en_US");

        pl_PL.setOnMouseClicked(mouseEvent -> {
            changeLocale("pl_PL");
        });

        en_EN.setOnMouseClicked(mouseEvent -> {
            changeLocale("en_US");
        });

        if (currentLocale.toString().equals("pl_PL")) {
            pl_PL.setSelected(true);
            en_EN.setSelected(false);
        } else {
            pl_PL.setSelected(false);
            en_EN.setSelected(true);
        }

        langRadiosHbox.getChildren().add(pl_PL);
        langRadiosHbox.getChildren().add(en_EN);

        for (Difficulty difficulty : Difficulty.values()) {
            RadioButton r = new RadioButton();
            String localizedDifficulty = bundle.getString(difficulty.name());
            r.setText(localizedDifficulty);
            r.setId(difficulty.name());
            r.setToggleGroup(difficultyToggleGroup);
            if (difficulty.name().equals(Difficulty.values()[0].name())) {
                r.setSelected(true);
            }
            switch (r.getId()){
                case "EASY":
                    EASY = r;
                    break;
                case "NORMAL":
                    NORMAL = r;
                    break;
                case "HARD":
                    HARD = r;
                    break;
            }
            radiosVbox.getChildren().add(r);
        }

        // Autorzy
        ResourceBundle authorsBundle = ResourceBundle.getBundle("com.example.viewproject.AuthorsResourceBundle", currentLocale);
        String[] authors = (String[]) authorsBundle.getObject("authors");
        String localizedAuthorLabel = authorsBundle.getString("authorLabel");
        localizedAuthorLabel = localizedAuthorLabel + ": " + String.join(", ", authors);
        authorsText.setText(localizedAuthorLabel);
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
            ResourceBundle bundle = ResourceBundle.getBundle("com.example.viewproject.lang", currentLocale);

            Stage stage = new Stage();

            String localizedTitle = bundle.getString("gameTitle");
            String localizedDifficulty = bundle.getString(diff.name());
            stage.setTitle(localizedTitle + ": " + localizedDifficulty);
            Scene scene = new Scene(loader.load(), 600, 600);
            stage.setScene(scene);
            GameWindowController controller = loader.getController();
            controller.currentLocale = currentLocale;
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

    private void changeLocale(String lang) {
        switch (lang){
            case "pl_PL":
                currentLocale = new Locale("pl", "PL");
                break;
            case "en_US":
                currentLocale = Locale.US;
                break;
        }
        System.out.println(currentLocale);
        updateLabels();
    }

    private void updateLabels() {
        ResourceBundle bundle = ResourceBundle.getBundle("com.example.viewproject.lang", currentLocale);

        String localizedTitle = bundle.getString("gameTitle");
        String localizedEasy = bundle.getString("EASY");
        String localizedNormal = bundle.getString("NORMAL");
        String localizedHard = bundle.getString("HARD");
        String localizedDiffText = bundle.getString("difficultyText");
        String localizedWelcomeText = bundle.getString("welcomeText");
        String localizedNewGame = bundle.getString("newGameBtn");

        // Autorzy
        ResourceBundle authorsBundle = ResourceBundle.getBundle("com.example.viewproject.AuthorsResourceBundle", currentLocale);
        String[] authors = (String[]) authorsBundle.getObject("authors");
        String localizedAuthorLabel = authorsBundle.getString("authorLabel");
        localizedAuthorLabel = localizedAuthorLabel + ": " + String.join(", ", authors);

        authorsText.setText(localizedAuthorLabel);
        newGameBtn.setText(localizedNewGame);
        EASY.setText(localizedEasy);
        NORMAL.setText(localizedNormal);
        HARD.setText(localizedHard);
        welcomeText.setText(localizedWelcomeText);
        difficultyText.setText(localizedDiffText);

        primaryStage.setTitle(localizedTitle);
    }
}
