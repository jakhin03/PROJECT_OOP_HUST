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

	/*
	 * public SuKien(String ten, String thoi_gian, String dia_diem,
	 * HistoricalDynasty nien_dai, HistoricalFigure nhan_vat_lien_quan) {
	 * this.ten = ten;
	 * this.thoi_gian = thoi_gian;
	 * this.dia_diem = dia_diem;
	 * this.nien_dai = nien_dai;
	 * this.nhan_vat_lien_quan = nhan_vat_lien_quan;
	 * }
	 */
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
		return (diaDiem.equals("")) ? "Đang cập nhật" : diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}

	public Dynasty getNien_dai() {
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

