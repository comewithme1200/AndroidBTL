package com.example.myapplication.helper;

public class UserHelper {
    String name,username,mail,phone,pass,decen;

    public UserHelper() {

    }

    public UserHelper(String name, String username, String mail, String phone, String pass, String decen) {
        this.name = name;
        this.username = username;
        this.mail = mail;
        this.phone = phone;
        this.pass = pass;
        this.decen = decen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDecen() {
        return decen;
    }

    public void setDecen(String decen) {
        this.decen = decen;
    }
}
