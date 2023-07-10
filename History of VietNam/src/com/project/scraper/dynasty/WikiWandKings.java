package com.project.scraper.dynasty;

import java.util.LinkedList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.project.historydatabase.figure.King;
import com.project.scraper.AScraper;
import com.project.scraper.IScraper;

public class WikiWandKings extends AScraper implements IScraper {
	private String tenTrieuDai;
	private LinkedList<King> kings;

	public String getTenTrieuDai() {
		return tenTrieuDai;
	}

	public WikiWandKings(String tenTrieuDai) {
		this.tenTrieuDai = tenTrieuDai;
		this.kings = new LinkedList<King>();
		String url = "https://www.wikiwand.com/vi/B%E1%BA%A3n_m%E1%BA%ABu:Danh_s%C3%A1ch_vua_v%C3%A0_ho%C3%A0ng_%C4%91%E1%BA%BF_Vi%E1%BB%87t_Nam";
		setUrl(url);
		connectToUrl();
	}
	
	@Override
	public void scrape() {
		Elements trData = this.getDoc().select("#content-root > article > span > div > table > tbody > tr");
		for (Element e : trData) {
			Elements thData = e.select("> th > a");
			Elements tdData = e.select("> td  a");
			if (thData.text().equals(this.tenTrieuDai)
					|| (this.tenTrieuDai.equals("Nhà Hậu Lê") && thData.text().equals("Nhà Lê trung hưng"))
					|| (this.tenTrieuDai.equals("Họ Khúc") && thData.text().equals("Tự chủ"))
					|| (this.tenTrieuDai.equals("Bắc thuộc lần III") && thData.text().contains("Bắc thuộc lần ba"))) {
				for (Element e1 : tdData) {
					kings.add(new King(e1.text()));
				}
			}
		}

	}

	public LinkedList<King> getKings() {
		return kings;
	}
}