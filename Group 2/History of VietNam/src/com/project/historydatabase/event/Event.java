package com.project.historydatabase.event;

import com.project.historydatabase.figure.*;
import com.project.historydatabase.figure.Character;
import com.project.historydatabase.dynasty.*;

public class Event {
	private String ten;
	private String thoiGian;
	private String diaDiem;
	private Dynasty nienDai;
	private Character nhanVatLienQuan;


	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(String thoiGian) {
		this.thoiGian = thoiGian;
	}

	public String getDiaDiem() {		
		return (diaDiem == null) ? "Đang cập nhật" : diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}

	public Dynasty getNienDai() {
		return nienDai;
	}

	public void setNienDai(Dynasty nienDai) {
		this.nienDai = nienDai;
	}

	public Figure getNhanVatLienQuan() {
		return nhanVatLienQuan;
	}

	public void setNhanVatLienQuan(String ten) {
		Character character = new Character(ten) {
		};
		this.nhanVatLienQuan = character;
	}
}

