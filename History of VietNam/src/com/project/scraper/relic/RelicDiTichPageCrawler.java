package com.project.scraper.relic;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.project.scraper.AScraper;
import com.project.scraper.IScraper;

public class RelicDiTichPageCrawler extends AScraper implements IScraper {
	private String type;
	private String rank;
	private String person;
	private String name;
	private String address;
	
	public RelicDiTichPageCrawler(String url) {
		this.setUrl(url);
		connectToUrl();
	}

	public void scrape() {
		this.name = this.getDoc().select("#block-harvard-content > article > div > section > div > div.hl__library-info__features > section > h2").text();
		
		this.address = this.getDoc().select("#block-harvard-content > article > div > section > div > div.hl__library-info__sidebar > div:nth-child(1) > section > div > div > div.hl__contact-info__address > span").text();

		
		StringBuilder personB = new StringBuilder();
		StringBuilder rankB = new StringBuilder();
		StringBuilder typeB = new StringBuilder();
		Elements info = this.getDoc().select("#block-harvard-content > article > div > section > div > div.hl__library-info__features > section  div > span:nth-child(2)");
		for (Element e: info) {
			if (e.text() != "") {
				if (e.text().contains("Loại hình di tích")) {
					typeB.append(e.text());
				}else if (e.text().contains("Xếp hạng")){
					rankB.append(e.text());
				}else if (e.text().contains("Đối tượng thờ")) {
					personB.append(e.text());
				}
			}
		}
		
		
		if (typeB.length() == 0){
			this.type = "Unknown";
		}else {
			this.type = typeB.toString().replace("Loại hình di tích: ", "");
		}
		
		if (rankB.length() == 0){
			this.rank = "Unknown";
		}else {
			this.rank = rankB.toString().replace("Xếp hạng: ", "");
		}
		
		if (personB.length() == 0){
			this.person = "Unknown";
		}else {
			this.person = personB.toString().replace("Đối tượng thờ: ", "");
		}	
	}
	
	public String getType() {
		return type;
	}

	public String getRank() {
		return rank;
	}

	public String getPerson() {
		return person;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
}