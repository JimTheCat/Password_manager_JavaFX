package com.project.password_manager;

public class Password {
    private String Password;
    private String Name;
    private String Category;
    private String Page;
    private String Login;

    public Password(String password, String nameOfPassword, String nameOfCategory, String page, String login) {
        this.Password = password;
        this.Name = nameOfPassword;
        this.Category = nameOfCategory;
        this.Page = page;
        this.Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public String getPage() {
        return Page;
    }

    public void setPage(String page) {
        this.Page = page;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        this.Login = login;
    }
}
