package com.example.myapplication;

import java.io.Serializable;

public class ingredients implements Serializable {
    int maNL;
    String tenNL;
    int giaNL;
    String donvi;
    Double soluong;

    public ingredients(int maNL, String tenNL, int giaNL, String donvi, Double soluong) {
        this.maNL = maNL;
        this.tenNL = tenNL;
        this.giaNL = giaNL;
        this.donvi = donvi;
        this.soluong = soluong;
    }

    public int getMaNL() {
        return maNL;
    }

    public void setMaNL(int maNL) {
        this.maNL = maNL;
    }

    public String getTenNL() {
        return tenNL;
    }

    public void setTenNL(String tenNL) {
        this.tenNL = tenNL;
    }

    public int getGiaNL() {
        return giaNL;
    }

    public void setGiaNL(int giaNL) {
        this.giaNL = giaNL;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public Double getSoluong() {
        return soluong;
    }

    public void setSoluong(Double soluong) {
        this.soluong = soluong;
    }
}
