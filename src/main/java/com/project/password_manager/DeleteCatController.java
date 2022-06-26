package com.project.password_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class DeleteCatController {

    ObservableList<Password> listToReturn = FXCollections.observableArrayList();
    public ObservableList<Password> listOfPasswords;
    public Password[] categoriesToReturn;
    public Password[] nameOfCategories;
    @FXML
    public TextField catLabel;

    public void deleteCatButton(ActionEvent event) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (Password singlePassword : listOfPasswords){
            if(!Objects.equals(singlePassword.getCategory(), catLabel.getText())) listToReturn.add(singlePassword);
        }
        for (int i = 0; i < nameOfCategories.length; i++){
            if(nameOfCategories[i] == null) continue;
            if(Objects.equals(nameOfCategories[i].getText(), catLabel.getText())) indexes.add(i);
        }
        categoriesToReturn = new Password[nameOfCategories.length - indexes.size()];
        for (int k : indexes) {
            for (int i = k; i < nameOfCategories.length - 1; i++) {
                nameOfCategories[i] = nameOfCategories[i+1];
            }
        }
        for (int i = 0; i < categoriesToReturn.length; i++){
            categoriesToReturn[i] = nameOfCategories[i];
        }
        ((Stage) catLabel.getScene().getWindow()).close();
    }

    public void cancelButton(ActionEvent event) {
    }

    protected ObservableList<Password> listOfPasswordsToReturn() {return listToReturn;}

    protected Password[] nameOfCategoriesToReturn() {return categoriesToReturn;}

    protected void getNameOfCategories(Password[] arrayFromAnotherController){
        if (arrayFromAnotherController == null)  nameOfCategories = new Password[1];
        else {
            nameOfCategories = new Password[arrayFromAnotherController.length + 1];
            System.arraycopy(arrayFromAnotherController, 0, nameOfCategories, 0, arrayFromAnotherController.length);
        }
    }

    protected void getListOfPasswords(ObservableList<Password> list){
        listOfPasswords = list;
    }
}
