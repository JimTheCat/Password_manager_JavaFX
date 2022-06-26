package com.project.password_manager;

public class Password {
    private String Text;
    private String Password;
    private String Name;
    private String Category;
    private String Page;
    private String Login;

    public Password(String text){
        this.Text = text;
    }

    public Password(String password, String nameOfPassword, String nameOfCategory, String page, String login) {
        this.Password = password;
        this.Name = nameOfPassword;
        this.Category = nameOfCategory;
        this.Page = page;
        this.Login = login;
    }

    public void decryptPassword(){
        this.Password = decrypting(this.Password);
        this.Login = decrypting(this.Login);
        this.Category = decrypting(this.Category);
        this.Name = decrypting(this.Name);
        this.Page = decrypting(this.Page);
    }

    public void decryptText(){
        this.Text = decrypting(this.Text);
    }

    private String decrypting(String Combine){
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

    public void encryptPassword(){
        this.Password = encrypting(this.Password);
        this.Login = encrypting(this.Login);
        this.Category = encrypting(this.Category);
        this.Name = encrypting(this.Name);
        this.Page = encrypting(this.Page);
    }

    public void encryptText(){
        this.Text = encrypting(this.Text);
    }

    private String encrypting(String Combine){
        StringBuilder hash = new StringBuilder();
        hash.append((char) ((int) (Math.random() * 94) + 33));

        int key = hash.charAt(0);

        for (int i = 0; i < Combine.length(); i++){
            hash.append((char) ((Combine.charAt(i) + key) % 94 + 33));
            hash.append((char) ((int) (Math.random() * 94) + 33));
        }

        return hash.toString();
    }

    public boolean compareTo(String passwordToCompare){
        return this.Text.equals(passwordToCompare);
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

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
