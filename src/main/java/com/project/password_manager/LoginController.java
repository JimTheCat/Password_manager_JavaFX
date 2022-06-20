package com.project.password_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

public class LoginController {

    private Password passwordFromFile;
    private File fileToLoad;
    private String nameOfFile;

    @FXML
    public PasswordField password;
    @FXML
    public Button buttonLogIn;
    @FXML
    public Label isPasswordCorrect;
    @FXML
    public Label fileStatus;
    @FXML
    public Button chooseFile;

    public void filePicker(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
        );

        fileToLoad = fileChooser.showOpenDialog(null);

        if (fileToLoad != null) {
            if (fileToLoad.length() == 0) newFile();
            loadPassword();
            nameOfFile = fileToLoad.getName();
            fileStatus.setText(nameOfFile);
            buttonLogIn.setDisable(false);
            password.setDisable(false);
        }
    }

    private void newFile(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Twoj plik jest pusty");
        alert.setHeaderText("Twoj plik nie zawiera zadnej tresci\nCzy chcesz utworzyc w nim baze hasel?");
        alert.showAndWait();
    }

    private void loadPassword() {

        try {
            passwordFromFile = new Password(new BufferedReader(new FileReader(fileToLoad)).readLine());
            //            passwordFromFile.decryptPassword();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void checkPassword(ActionEvent event) throws IOException {
        if (passwordFromFile == null) return;

        if (passwordFromFile.compareTo(password.getText())){
            isPasswordCorrect.setText("");
            Stage primaryStage = new Stage(StageStyle.DECORATED);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/MainPanel.fxml"));
            Parent root = loader.load();
            MainPanelController mainPanelController = loader.getController();
            mainPanelController.setFileFromLoginPanel(fileToLoad);
            mainPanelController.loadFileToTableView();

            Scene scene = new Scene(root);
            primaryStage.setTitle(nameOfFile + " - Password Manager s25256");
            primaryStage.setScene(scene);
            primaryStage.show();
            ((Stage)password.getScene().getWindow()).close();
        }
        else {
            isPasswordCorrect.setText("Podales bledne haslo! Sprobuj ponownie!");
        }
    }
}
