package com.project.historydatabase.figure;

public abstract class Figure {
    protected String namSinh;
    protected String namMat;
    protected String ten;
    protected String tenKhac;
    protected String queQuan;
    protected String danToc;
    protected String namNhapNgu;
    protected String ghiChu;
    
    public Figure() {
    	
    }

    public Figure(String ten, String namSinh, String namMat) {
        this.namSinh = namSinh;
        this.namMat = namMat;
        this.ten = ten;
    }
    
    public Figure(String ten) {
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
    
	public String getTenKhac() {
		return tenKhac;
	}
	
	public void setTenKhac(String tenKhac) {
		this.tenKhac = tenKhac;
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
	
	public String namNhapNgu() {
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
	
	

	
}
