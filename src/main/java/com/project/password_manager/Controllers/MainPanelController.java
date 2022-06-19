package com.project.password_manager.Controllers;

import com.project.password_manager.Utils.Category;
import com.project.password_manager.Utils.Password;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;

public class MainPanelController {

    @FXML
    public MenuItem openFileMenu;

    @FXML
    public TableColumn idColumn;
    @FXML
    public TableColumn nameColumn;
    @FXML
    public TableColumn categoryColumn;
    @FXML
    public TableColumn pageColumn;

    private final ObservableList<Password> listOfPasswords = FXCollections.observableArrayList(
            new Password("Testowe Haslo", "nameOfPassword", "New Category", "", "")
    );

    
}
