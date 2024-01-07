package com.example.viewproject;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class App extends Application {

    Locale currentLocale = new Locale("pl", "PL");
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("new-game.fxml"));

        // WYBOR LOKALIZACJI DLA CALEJ APLIKACJI
        currentLocale = new Locale("pl", "PL");
        ResourceBundle bundle = ResourceBundle.getBundle("com.example.viewproject.lang", currentLocale);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("new-game.fxml"), bundle);

        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        NewGameController controller = fxmlLoader.getController();

        controller.currentLocale = currentLocale;

        controller.init();
        String localizedTitle = bundle.getString("gameTitle");
        stage.setTitle(localizedTitle);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}