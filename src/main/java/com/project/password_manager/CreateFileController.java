package com.project.password_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.File;

public class CreateFileController {
    @FXML
    public Label nameOfFile;
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
        }
        else isSamePasswords.setText("Hasła nie są takie same!");
    }

    protected void setNameOfFile(File file){
        nameOfFile.setText(file.getName());
    }
}
