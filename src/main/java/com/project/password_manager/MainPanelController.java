package com.project.password_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class MainPanelController {

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

    public void openFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt")
        );

        File fileToLoad = fileChooser.showOpenDialog(null);

        if (fileToLoad != null) loadFileToTableView(fileToLoad);
    }

    private boolean auth(String password){
        //TODO: Ogarnac filmik z scenami :3
        return true;
    }

    private void loadFileToTableView(File fileToLoad) {
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileToLoad));
            String line;
            String[] array;
            boolean firstLine = true;
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("Password"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<>("Category"));
            pageColumn.setCellValueFactory(new PropertyValueFactory<>("Page"));
            loginColumn.setCellValueFactory(new PropertyValueFactory<>("Login"));

            while ((line = br.readLine()) != null) {
                if (firstLine){
                    firstLine = false;
                    if(auth(line)) continue;
                    //TODO: Exception reeee nie pamietam xD
//                    else throw Exception;
                }
                array = line.split(" ");
                list.add(new Password(array[0], array[1], array[2], array[3], array[4]));
            }

            table.setItems(list);

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}