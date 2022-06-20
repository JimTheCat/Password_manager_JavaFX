package com.project.password_manager;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class FileManager extends File {

    public FileManager(String pathname) {
        super(pathname);
    }

    public void createFile(Password password){
        try {
            this.createNewFile();
            PrintWriter writer = new PrintWriter(this, StandardCharsets.UTF_8);
            writer.println(password);
            writer.println(" ");
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
