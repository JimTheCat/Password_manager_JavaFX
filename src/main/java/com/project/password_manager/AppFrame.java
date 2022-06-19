package com.project.password_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppFrame extends Application {

    private static Stage loginStage;

    @Override
    public void start(Stage stage) throws IOException {
        loginStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(AppFrame.class.getResource("FXML/MainPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Password Manager s25256");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
