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
            password.encryptText();
            PrintWriter writer = new PrintWriter(this, StandardCharsets.UTF_8);
            writer.println(password.getText());
            writer.println(" ");
            password.decryptText();
            System.out.println(password.getText());
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void savingCurrentChanges(Password password, Password[] categories, ObservableList<Password> listOfPasswords){
        try {
            PrintWriter writer = new PrintWriter(this, StandardCharsets.UTF_8);
            password.encryptPassword();
            for(Password i : categories){
                i.encryptText();
            }

            for(Password j : listOfPasswords){
                j.encryptPassword();
            }
            writer.println(password);
            writer.println(Arrays.toString(categories));
            writer.println(Arrays.toString(listOfPasswords.toArray()));
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
