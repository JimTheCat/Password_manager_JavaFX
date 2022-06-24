package com.project.password_manager;

import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class FileManager extends File {

    public FileManager(String pathname) {
        super(pathname);
    }

    public void createFile(Password password){
        try {
            this.createNewFile();
            String passwordToPush = password.encrypt(String.valueOf(password));
            PrintWriter writer = new PrintWriter(this, StandardCharsets.UTF_8);
            writer.println(passwordToPush);
            writer.println(" ");
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void savingCurrentChanges(Password password, String[] categories, ObservableList<Password> listOfPasswords){
        try {
            PrintWriter writer = new PrintWriter(this, StandardCharsets.UTF_8);
            writer.println(password);
            writer.println(Arrays.toString(categories));
            writer.println(Arrays.toString(listOfPasswords.toArray()));
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
