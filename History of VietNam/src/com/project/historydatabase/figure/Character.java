package com.project.historydatabase.figure;

public class Character extends Figure {
    private String queQuan;
    private String danToc;
    private String namNhapNgu;
    private String ghiChu;
    private String namDoTrangNguyen;
    private String tenKhac;
    private King doiVua;

    public Character(String ten, String namSinh, String namMat, String queQuan, String danToc, String namNhapNgu, String ghiChu, String namDoTrangNguyen)  {
        super(ten, namSinh, namMat);
        this.queQuan = queQuan;
        this.danToc = danToc;
        this.namNhapNgu = namNhapNgu;
        this.ghiChu = ghiChu;
        this.namDoTrangNguyen = namDoTrangNguyen;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getNamNhapNgu() {
        return namNhapNgu;
    }

    public void setNamNhapNgu(String namNhapNgu) {
        this.namNhapNgu = namNhapNgu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getNamDoTrangNguyen() {
        return namDoTrangNguyen;
    }

    public void setNamDoTrangNguyen(String namDoTrangNguyen) {
        this.namDoTrangNguyen = namDoTrangNguyen;
    }

    public String getTenKhac() {
        return tenKhac;
    }

    public void setTenKhac(String tenKhac) {
        this.tenKhac = tenKhac;
    }

    public King isDoiVua() {
        return doiVua;
    }

    public void setDoiVua(King doiVua) {
        this.doiVua = doiVua;
    }
}
