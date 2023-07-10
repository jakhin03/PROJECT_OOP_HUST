package com.project.scraper.figure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.scraper.AScraper;
import com.project.scraper.IScraper;
import com.project.historydatabase.figure.Character;
import com.project.historydatabase.figure.King;

public class TrangNguyen extends AScraper implements IScraper {

	private ArrayList<Character> list = new ArrayList<Character>();

	public TrangNguyen() {
		String url = "https://modacaocap.com/danh-sach-trang-nguyen-viet-nam/";
		this.url = url;
		connectToUrl();
	}

	public ArrayList<Character> getList() {
		return list;
	}

	@Override
	public void scrape() {
		Element mainContent = this.doc.getElementsByClass("entry-content single-page").first();
		Element table = mainContent.getElementsByClass("wikitable sortable jquery-tablesorter").first();
		Element tableBody = table.select("tbody").first();
		Elements rows = tableBody.select("tr");
		for (Element row : rows) {
			Elements data = row.select("td");
			String ten = data.get(1).text();
			String time = data.get(2).text();
			String namSinh = "";
			String namMat = "";
			if (time.equals("")) {
				namSinh = namSinh.concat("Chưa biết");
				namMat = namMat.concat("Chưa biết");
			} else {
				int index = time.indexOf("-");
				namSinh = namSinh.concat(time.substring(0, index));
				namMat = namMat.concat(time.substring(index + 1));
				namMat = namMat.trim();
				if (namMat.equals("?")) {
					namMat = namMat.replace("?", "Chưa biết");
				}
			}

			String queQuan = data.get(3).text();
			String namDoTrangNguyen = data.get(4).text();
			String vua = data.get(5).text();
			String ghiChu = data.get(6).text();
			King doiVua = new King(vua);
			Character trang = new Character(ten, namSinh, namMat);
			trang.setDoiVua(doiVua);
			trang.setGhiChu(ghiChu);
			trang.setNamDoTrangNguyen(namDoTrangNguyen);
			trang.setQueQuan(queQuan);
			list.add(trang);
			System.out.printf("%s: %s + %s %s %s %s %s\n", ten, namSinh, namMat, queQuan, namDoTrangNguyen, vua,
					ghiChu);
		}
	}

	public static void main(String[] args) {

		TrangNguyen trangNguyen = new TrangNguyen();
		trangNguyen.scrape();
		String filePath = "src\\testTrangNguyen.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			ArrayList<Character> list = new ArrayList<Character>();
			list.addAll(trangNguyen.getList());
			gson.toJson(list, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}