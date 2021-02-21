package com.example.myapplication;

import java.io.Serializable;

public class user implements Serializable {
    int maNV;
    String username;
    String password;
    String decen;

    public user(int maNV, String username, String password, String decen) {
        this.maNV = maNV;
        this.username = username;
        this.password = password;
        this.decen = decen;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDecen() {
        return decen;
    }

    public void setDecen(String decen) {
        this.decen = decen;
    }
}
