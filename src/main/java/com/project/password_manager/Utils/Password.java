package com.project.password_manager.Utils;

public class Password {
    private String password;
    private String nameOfPassword;
    private String nameOfCategory;
    private String page;
    private String login;

    public Password(String password, String nameOfPassword, String nameOfCategory, String page, String login) {
        this.password = password;
        this.nameOfPassword = nameOfPassword;
        this.nameOfCategory = nameOfCategory;
        this.page = page;
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameOfPassword() {
        return nameOfPassword;
    }

    public void setNameOfPassword(String nameOfPassword) {
        this.nameOfPassword = nameOfPassword;
    }

    public String getNameOfCategory() {
        return nameOfCategory;
    }

    public void setNameOfCategory(String nameOfCategory) {
        this.nameOfCategory = nameOfCategory;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
