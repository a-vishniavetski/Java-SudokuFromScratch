package com.example.viewproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {

    Locale currentLocale = new Locale("pl", "PL");
    Stage STAGE;
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("new-game.fxml"));
        STAGE = stage;
        // WYBOR LOKALIZACJI DLA CALEJ APLIKACJI
        currentLocale = new Locale("pl", "PL");
        ResourceBundle bundle = ResourceBundle.getBundle("com.example.viewproject.lang", currentLocale);

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("new-game.fxml"), bundle);

        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        NewGameController controller = fxmlLoader.getController();

        controller.currentLocale = currentLocale;

        controller.init(STAGE);
        String localizedTitle = bundle.getString("gameTitle");
        stage.setTitle(localizedTitle);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void setTitle(String title) {
        STAGE.setTitle(title);
    }

    public static void main(String[] args) {
        launch();
    }
}