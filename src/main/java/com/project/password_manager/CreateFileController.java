package com.project.password_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class CreateFileController {
    @FXML
    public Label nameOfFile;
    private File fileFromAnotherController;
    @FXML
    public PasswordField firstPassword;
    @FXML
    public PasswordField secondPassword;
    @FXML
    public Label isSamePasswords;
    @FXML
    public Button createButton;

    public void createData(ActionEvent actionEvent) {
        if (firstPassword.getText().equals(secondPassword.getText())){
            isSamePasswords.setText("");
            FileManager fileManager = new FileManager(fileFromAnotherController.getPath());
            Password passwordProgram = new Password(firstPassword.getText());
            fileManager.createFile(passwordProgram);
            try {
                Stage primaryStage = new Stage(StageStyle.DECORATED);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/MainPanel.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                primaryStage.setTitle(nameOfFile.getText() + " - Password Manager s25256");
                primaryStage.setScene(scene);
                primaryStage.show();
                ((Stage) createButton.getScene().getWindow()).close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        else isSamePasswords.setText("Hasła nie są takie same!");
    }

    protected void setNameOfFile(File file){
        nameOfFile.setText(file.getName());
    }

    protected void setFile (File file) {fileFromAnotherController = file;}
}
