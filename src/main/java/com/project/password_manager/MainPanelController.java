package com.project.password_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MainPanelController {

    public File fileFromLoginPanel;

    @FXML
    public MenuItem openFileMenu;
    public TableView<Password> table;
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

    ObservableList<Password> list = FXCollections.observableArrayList();

    public void setFileFromLoginPanel(File fileFromLogin){
        fileFromLoginPanel = fileFromLogin;
    }

    public void openFile() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
        );

        File fileToLoad = fileChooser.showOpenDialog(null);

        if (fileToLoad != null) loadFileToTableView();
    }

    private boolean auth(String password, ActionEvent event){
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("FXML/Login.fxml"));
////            LoginController loginController = fxmlLoader.getController();
////            loginController.passwordToCheck(password);
//            Scene scene = new Scene(fxmlLoader.load());
//            Stage stage = new Stage();
////            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//            stage.setTitle("Logowanie");
//            stage.setScene(scene);
//            stage.show();
//
//
//
//        } catch (IOException e){
//            e.printStackTrace();
//        }
        return true;
    }

    protected void loadFileToTableView() {
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileFromLoginPanel));
            String line;
            String[] array;
            boolean firstLine = true;
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
            pageColumn.setCellValueFactory(new PropertyValueFactory<>("Page"));
            loginColumn.setCellValueFactory(new PropertyValueFactory<>("Login"));
            Password passwordFromFile = new Password(br.readLine());
            String[] arrayOfCategories = br.readLine().split(" ");
            Password[] categories = new Password[arrayOfCategories.length];

            for (int i = 0; i < categories.length; i++){
                categories[i] = new Password(arrayOfCategories[i]);
            }
            while ((line = br.readLine()) != null) {
                array = line.split(" ");
                list.add(new Password(array[0], array[1], array[2], array[3], array[4]));
            }
            if (list.isEmpty()) return;
            table.setItems(list);

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}