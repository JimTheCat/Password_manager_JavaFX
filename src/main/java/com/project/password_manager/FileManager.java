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
            PrintWriter writer = new PrintWriter(this, StandardCharsets.UTF_8);
            writer.println(password.getText());
            writer.println(" ");
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void savingCurrentChanges(Password password, Password[] categories, ObservableList<Password> listOfPasswords){
        try {
            PrintWriter writer = new PrintWriter(this, StandardCharsets.UTF_8);
            writer.println(password.getText());
            for(Password i : categories){
                if (i == null) continue;
                i.encryptText();
                writer.print(i.getText() + " ");
                i.decryptText();
            }
            writer.println("");
            for(Password j : listOfPasswords){
                j.encryptPassword();
                writer.println(j.getPassword() + " " + j.getName() + " " + j.getCategory() + " " + j.getPage() + " " + j.getLogin() + " ");
                j.decryptPassword();
            }
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


}
