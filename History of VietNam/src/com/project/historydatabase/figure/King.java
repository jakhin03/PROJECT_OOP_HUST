package com.project.historydatabase.figure;

public class King extends Figure {
    private String mieuHieu;
    private String thuyHieu;
    private String nienHieu;
    private String tenHuy;
    private String theThu;
    private int namTriVi;

    public King(int namSinh, int namMat, String ten, String mieuHieu, String thuyHieu, String nienHieu, String tenHuy, String theThu, int namTriVi) {
    	super(ten, namSinh, namMat);
        this.mieuHieu = mieuHieu;
        this.thuyHieu = thuyHieu;
        this.nienHieu = nienHieu;
        this.tenHuy = tenHuy;
        this.theThu = theThu;
        this.namTriVi = namTriVi;
    }


    public String getMieuHieu() {
        return mieuHieu;
    }

    public void setMieuHieu(String mieuHieu) {
        this.mieuHieu = mieuHieu;
    }

    public String getThuyHieu() {
        return thuyHieu;
    }

    public void setThuyHieu(String thuyHieu) {
        this.thuyHieu = thuyHieu;
    }

    public String getNienHieu() {
        return nienHieu;
    }

    public void setNienHieu(String nienHieu) {
        this.nienHieu = nienHieu;
    }

    public String getTenHuy() {
        return tenHuy;
    }

    public void setTenHuy(String tenHuy) {
        this.tenHuy = tenHuy;
    }

    public String getTheThu() {
        return theThu;
    }

    public void setTheThu(String theThu) {
        this.theThu = theThu;
    }

    public int getNamTriVi() {
        return namTriVi;
    }

    public void setNamTriVi(int namTriVi) {
        this.namTriVi = namTriVi;
    }
}

