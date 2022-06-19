package com.project.password_manager;

import java.util.ArrayList;
import java.util.Objects;

public class Manager {
    private ArrayList<String> nameOfCategory;

    public Manager() {}

    public String decrypt(String Combine){
        StringBuilder password = new StringBuilder();
        int counter = 1;

        int key = 122 - Combine.charAt(0);

        for (int i = 0; i < Combine.length(); i++){
            if (counter % 2 == 0){
                password.append((char) ((Combine.charAt(i) + key) % 94 + 33));
            }
            counter += 1;
        }
        return password.toString();
    }

    public String encrypt(String Combine){
        StringBuilder hash = new StringBuilder();
        hash.append(((int) (Math.random() * 94) + 33));

        int key = hash.charAt(0);

        for (int i = 0; i < Combine.length(); i++){
            hash.append((char) ((Combine.charAt(i) + key) % 94 + 33));
            hash.append(((int) (Math.random() * 94) + 33));
        }

        return hash.toString();
    }

    public String showCategory(String name){
        for (String s : nameOfCategory) {
            if (Objects.equals(name, s)) return s;
        }
        return "";
    }

    public void addCategory(String name){
        nameOfCategory.add(name);
    }

    boolean checkPassword (String password){
        return password.contentEquals("iB:@HCo2m<j3rt!/ZAP:<=v]7^[_@");
    }

}
