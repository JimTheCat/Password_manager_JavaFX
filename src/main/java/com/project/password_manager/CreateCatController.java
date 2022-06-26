package com.project.password_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateCatController {
    public Password[] nameOfCategories;
    @FXML
    public TextField catLabel;

    public void addCatButton(ActionEvent event) {
        Password category = new Password(catLabel.getText());
        nameOfCategories[nameOfCategories.length-1] = category;

        ((Stage) catLabel.getScene().getWindow()).close();
    }

    public void cancelButton(ActionEvent event) {
    }

    protected void getNameOfCategories(Password[] arrayFromAnotherController){
        if (arrayFromAnotherController == null)  nameOfCategories = new Password[1];
        else {
            nameOfCategories = new Password[arrayFromAnotherController.length + 1];
            System.arraycopy(arrayFromAnotherController, 0, nameOfCategories, 0, arrayFromAnotherController.length);
        }
    }

    protected Password[] returnCategories(){
        return nameOfCategories;
    }
}
