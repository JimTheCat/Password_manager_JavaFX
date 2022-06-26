package com.project.password_manager;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class NewRecordController {
    ObservableList<Password> listOfPassword;
    @FXML
    public Label errorLabel;
    @FXML
    public PasswordField passwordLabel;
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

    public Password passwordToReturn;

    public void addPassword() {
        if (categoryLabel.getValue() == null) {
            errorLabel.setText("Pola obowiązkowe nie zostały wypełnione!");
            return;
        }
        boolean flag = (!passwordLabel.getText().isEmpty() && !nameLabel.getText().isEmpty() && !categoryLabel.getValue().isEmpty());
        if (flag) {
            passwordToReturn = new Password(passwordLabel.getText(), nameLabel.getText(), categoryLabel.getValue(), pageLabel.getText(), loginLabel.getText());
            if (Objects.equals(passwordToReturn.getPage(), "")) passwordToReturn.setPage("-");
            if (Objects.equals(passwordToReturn.getLogin(), "")) passwordToReturn.setLogin("-");
            ((Stage) passwordLabel.getScene().getWindow()).close();
        }
        else errorLabel.setText("Pola obowiązkowe nie zostały wypełnione!");
    }

    public void cancelAdding(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Czy na pewno chcesz anulowac?");
        alert.setHeaderText("Wszystkie dotychczasowe zmiany w tym oknie nie zostaną zapisane!");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty() || result.get() != ButtonType.OK) return;
        else ((Stage)passwordLabel.getScene().getWindow()).close();
    }

    protected void setComboBox(Password[] categories){
        if (categories.length == 0) return;
        for (Password i : categories){
            categoryLabel.getItems().add(i.getText());
        }

    }

    protected Password returnPassword(){
        return passwordToReturn;
    }

}
