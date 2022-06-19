package com.project.password_manager;

import java.util.ArrayList;
import java.util.Objects;

public class Manager {
    private ArrayList<String> nameOfCategory;

    public Manager() {}




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
