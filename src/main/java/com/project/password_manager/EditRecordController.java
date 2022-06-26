package com.project.password_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class EditRecordController {

    Password passwordToReturn;
    @FXML
    public TextField nameLabel;
    @FXML
    public ComboBox<String> categoryLabel;
    @FXML
    public TextField pageLabel;
    @FXML
    public TextField loginLabel;
    @FXML
    public Button addButton;
    @FXML
    public Button cancelButton;
    @FXML
    public PasswordField passwordLabel;

    public void editPassword(ActionEvent event) {
//        passwordToReturn = new Password(passwordLabel.getText(), nameLabel.getText(), categoryLabel.getValue(), pageLabel.getText(), loginLabel.getText());
        passwordToReturn.setPassword(passwordLabel.getText());
        passwordToReturn.setName(nameLabel.getText());
        passwordToReturn.setCategory(categoryLabel.getValue());
        passwordToReturn.setPage(pageLabel.getText());
        passwordToReturn.setLogin(loginLabel.getText());
        ((Stage)passwordLabel.getScene().getWindow()).close();
    }

    public void cancelEditing(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Czy na pewno chcesz anulowac?");
        alert.setHeaderText("Wszystkie dotychczasowe zmiany w tym oknie nie zostaną zapisane!");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty() || result.get() != ButtonType.OK) return;
        else ((Stage)passwordLabel.getScene().getWindow()).close();
    }

    protected void setComboBox(Password[] categories){
        for (Password i : categories){
            categoryLabel.getItems().add(i.getText());
        }
    }

    protected void inflateUI(Password password){
        passwordToReturn = password;
        passwordLabel.setText(password.getPassword());
        nameLabel.setText(password.getName());
        pageLabel.setText(password.getPage());
        loginLabel.setText(password.getLogin());
    }

    protected Password returnEditedPassword(){
        return passwordToReturn;
    }
}
