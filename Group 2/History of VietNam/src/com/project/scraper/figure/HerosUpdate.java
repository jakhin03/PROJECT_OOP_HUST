package com.project.scraper.figure;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.project.scraper.AScraper;
import com.project.scraper.IScraper;
import com.project.historydatabase.figure.Character;
public class HerosUpdate extends AScraper implements IScraper {
	private ArrayList<Character> list = new ArrayList<Character>();

	public ArrayList<Character> getList() {
		return list;
	}

	public HerosUpdate() {
		String url = "https://doanhnghiepvn.vn/kham-pha/chan-dung-10-anh-hung-trong-khang-chien-chong-phap/20200130024940748";
		setUrl(url);
		connectToUrl();
	}

	@Override
	public void scrape() {
		Element mainContent = doc.getElementById("abody");
		Elements paragraphs = mainContent.select("span:contains(sinh)");
		for (Element e : paragraphs) {
			String data = e.text();
			System.out.println(data);
			int index = data.indexOf(".");
			String infor = data.substring(0, index);
			if (infor.contains("Bế Văn Đàn")) {
				infor = infor.replace("Bế Văn Đàn, dân tộc Tày, sinh năm 1931",
						"Bế Văn Đàn sinh năm 1931, dân tộc Tày");
				infor = infor.replace(" tại", ", quê ở");
			}
			System.out.println(infor);
			int endName = infor.indexOf(" sinh");
			String ten = infor.substring(0, endName);
			System.out.println(ten);
			int startDOB = infor.indexOf("1");
			String namSinh = infor.substring(startDOB, startDOB + 4);
			System.out.println(namSinh);
			int startQueQuan = infor.indexOf("ở");
			String queQuan = infor.substring(startQueQuan);
			queQuan = queQuan.replace("ở ", "");
			System.out.println(queQuan);
			int startDanToc = infor.indexOf("dân");
			int endDanToc = infor.indexOf(", q");
			String danToc = infor.substring(startDanToc, endDanToc);
			danToc = danToc.replace("dân tộc ", "");
			System.out.println(danToc);
			String namNhapNgu = "";
			if (infor.contains("nhập ngũ")) {
				int startNamNhapNgu = infor.indexOf("nhập");
				namNhapNgu = namNhapNgu.concat(infor.substring(startNamNhapNgu));
				namNhapNgu = namNhapNgu.replace("nhập ngũ ", "");
				System.out.println(namNhapNgu);
			}
			Character character = new Character(ten);
			character.setNamSinh(namSinh);
			character.setNamMat("Chưa biết");
			character.setNamNhapNgu(namNhapNgu);
			character.setDanToc(danToc);
			character.setQueQuan(queQuan);
			String ghiChu = data.substring(index + 2);
			System.out.println(ghiChu);
			character.setGhiChu(ghiChu);
			list.add(character);
		}
	}
}
