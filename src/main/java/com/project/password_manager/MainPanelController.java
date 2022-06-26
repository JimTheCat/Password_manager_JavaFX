package com.project.password_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;


public class MainPanelController {

    public Password passwordFromAddController;
    public File fileFromLoginPanel;
    public TableView<Password> table;
    public Password passwordToUnlockApp;
    public ObservableList<Password> list = FXCollections.observableArrayList();
    public Password[] comboBoxContent;

    @FXML
    public MenuItem openFileMenu;
    @FXML
    public TableColumn<Password, String> passwordColumn;
    @FXML
    public TableColumn<Password, String> nameColumn;
    @FXML
    public TableColumn<Password, String> categoryColumn;
    @FXML
    public TableColumn<Password, String> pageColumn;
    @FXML
    public TableColumn<Password, String> loginColumn;

    public void initialize(){
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
        pageColumn.setCellValueFactory(new PropertyValueFactory<>("Page"));
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("Login"));
        table.setEditable(true);
        table.setItems(list);
        table.refresh();
    }

    public void setFileFromLoginPanel(File fileFromLogin){
        fileFromLoginPanel = fileFromLogin;
        System.out.println(fileFromLogin.getName());
    }

    protected void loadFileToTableView() {
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileFromLoginPanel));
            String line;
            String[] array;
            passwordToUnlockApp = new Password(br.readLine());
            String[] arrayOfCategories = br.readLine().split(" ");
            Password[] categories = new Password[arrayOfCategories.length];
            Password encryptedPassword;

            for (int i = 0; i < categories.length; i++){
                categories[i] = new Password(arrayOfCategories[i]);
                categories[i].decryptText();
            }
            if (categories.length != 0) {
                comboBoxContent = categories;
            }
            while ((line = br.readLine()) != null) {
                array = line.split(" ");
                encryptedPassword = new Password(array[0], array[1], array[2], array[3], array[4]);
                encryptedPassword.decryptPassword();
                list.add(encryptedPassword);
            }
            if (list.isEmpty()) return;
            table.setItems(list);

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void setPasswordToUnlockApp(Password password){
        passwordToUnlockApp = password;
    }
    public void addPasswordItem(ActionEvent event) {
        if (comboBoxContent == null || comboBoxContent.length == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Brak kategorii");
            alert.setHeaderText("Baza nie posiada żadnych kategorii");
            alert.showAndWait();
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/NewRecord.fxml"));
                Parent parent = loader.load();

                NewRecordController controller = loader.getController();
                controller.setComboBox(comboBoxContent);

                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Dodawanie hasła");
                stage.setScene(new Scene(parent));
                stage.showAndWait();

                passwordFromAddController = controller.returnPassword();
                list.add(passwordFromAddController);
                table.setItems(list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void editPasswordItem(ActionEvent event) {
        Password selectedForEdit;
        System.out.println(table.getSelectionModel().getSelectedItem().getPassword());
        if (table.getSelectionModel().getSelectedItem() == null){
            //tutaj alert odnośnie błędu
            return;
        }

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/EditRecord.fxml"));
            Parent parent = loader.load();

            EditRecordController controller = loader.getController();
            controller.setComboBox(comboBoxContent);
            controller.inflateUI(table.getSelectionModel().getSelectedItem());

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edytuj hasło");
            stage.setScene(new Scene(parent));
            stage.showAndWait();
//
//            selectedForEdit = controller.returnEditedPassword();
//            table.getSelectionModel().;
            table.refresh();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void deletePasswordItem(ActionEvent event) {

        Password selectedForDelete = table.getSelectionModel().getSelectedItem();
        System.out.println(selectedForDelete.getPassword());
        if (selectedForDelete == null){
            //tutaj alert odnośnie błędu
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usunięcie hasła");
        alert.setHeaderText("Czy na pewno chcesz usunąć hasło?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty() || result.get() != ButtonType.OK){
            if(result.get() == ButtonType.CANCEL) return;
        }
        else {
            table.getItems().removeAll(
                    table.getSelectionModel().getSelectedItems()
            );
        }
    }

    public void addCatItem(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/CreateCategory.fxml"));
            Parent parent = loader.load();

            CreateCatController controller = loader.getController();

            controller.getNameOfCategories(comboBoxContent);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Dodaj kategorie");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            comboBoxContent = controller.returnCategories();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void delCatItem(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/DeleteCategory.fxml"));
            Parent parent = loader.load();

            DeleteCatController controller = loader.getController();

            controller.getNameOfCategories(comboBoxContent);
            controller.getListOfPasswords(list);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Usuń kategorie");
            stage.setScene(new Scene(parent));
            stage.showAndWait();

            comboBoxContent = controller.nameOfCategoriesToReturn();
            list = controller.listOfPasswordsToReturn();
            table.setItems(list);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveFileItem(ActionEvent event) {
        FileManager fileManager = new FileManager(fileFromLoginPanel.getPath());
        fileManager.savingCurrentChanges(passwordToUnlockApp, comboBoxContent, list);
    }

    public void closeFileItem(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Zamknięcie aplikacji");
        alert.setHeaderText("Czy na pewno chcesz wyjść z aplikacji?\nUwaga twoje zmiany mogą zostać utracone!");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isEmpty() || result.get() != ButtonType.OK) return;
        else {
            try {
                Stage primaryStage = new Stage(StageStyle.DECORATED);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Login.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                primaryStage.setTitle("Password Manager s25256");
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e){
                e.printStackTrace();
            }
            ((Stage) table.getScene().getWindow()).close();
        }
    }
}