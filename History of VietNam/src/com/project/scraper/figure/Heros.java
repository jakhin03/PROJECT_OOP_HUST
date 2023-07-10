package com.project.scraper.figure;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.project.scraper.AScraper;
import com.project.scraper.IScraper;
import com.project.historydatabase.figure.Character;

public class Heros extends AScraper implements IScraper {
	private ArrayList<Character> list = new ArrayList<Character>();

	public ArrayList<Character> getList() {
		return list;
	}

	public Heros() {
		String url = "https://giaoduc.net.vn/nhung-duong-pho-mang-ten-cac-vi-tuong-linh-va-si-quan-quan-doi-post173227.gd";
		setUrl(url);
		connectToUrl();
	}

	@Override
	public void scrape() {
		Element mainBody = this.doc.getElementsByClass("details__content cms-body ").first();
		Elements paragraphs = mainBody.select("p");

		Elements hasNameparagraphs = mainBody.select("p:has(em)");
		int numOfParagraphs = hasNameparagraphs.size();
		List<Element> figureParagraph = hasNameparagraphs.subList(2, numOfParagraphs - 1);
		for (Element p : figureParagraph) {
			String ten = p.select("strong").first().text();
			System.out.println(ten);
			ten = ten.concat(" (");
			Element foundParagraph = findElement(ten, paragraphs);
			if (foundParagraph != null) {
				String data = foundParagraph.text();
				int index = data.indexOf(ten);
				String trimData = data.substring(index);
				System.out.println(trimData);
				String nam = findNamSinh(trimData);
				String queQuan = findQueQuan(trimData);
				System.out.println(queQuan);
				index = nam.indexOf("-");
				String namSinh = nam.substring(0, index);
				String namMat = nam.substring(index + 1);
				System.out.println(namSinh + " " + namMat);
				ten = ten.substring(0, ten.length() - 2);
				Character character = new Character(ten, namSinh, namMat);
				character.setGhiChu(trimData);
				character.setQueQuan(queQuan);
				list.add(character);
			}
		}
	}

	public Element findElement(String findStr, List<Element> figureElement) {
		for (Element p : figureElement) {
			String text = p.text();
			if (text.contains(findStr)) {
				return p;
			}
		}
		return null;
	}

	public String findNamSinh(String data) {
		int start = data.indexOf("(");
		int end = data.indexOf(")");
		if (start != -1 && end != -1) {
			String namSinh = data.substring(start + 1, end);
			return namSinh;
		}
		return null;
	}

	public String findQueQuan(String data) {
		if (data.contains("Hoàng Sâm")) {
			int start = data.indexOf("Quê");
			String queQuan = data.substring(start);
			return queQuan;
		} else if (data.contains("Nguyễn Phúc Lai")) {
			int start = data.indexOf("quê");
			String mainData = data.substring(start);
			int end = mainData.indexOf(".");
			String queQuan = mainData.substring(0, end);
			return queQuan;
		}
		int start = data.indexOf("quê");
		String mainData = data.substring(start);
		int end = mainData.indexOf(";");
		String queQuan = mainData.substring(0, end);
		return queQuan;
	}
}