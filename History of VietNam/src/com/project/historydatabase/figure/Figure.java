package com.project.historydatabase.figure;

public class Figure {
    protected String namSinh;
    protected String namMat;
    protected String ten;

    public Figure(String namSinh, String namMat, String ten) {
        this.namSinh = namSinh;
        this.namMat = namMat;
        this.ten = ten;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getNamMat() {
        return namMat;
    }

    public void setNamMat(String namMat) {
        this.namMat = namMat;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
