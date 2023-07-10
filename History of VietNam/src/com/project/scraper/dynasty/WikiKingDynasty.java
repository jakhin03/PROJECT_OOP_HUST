package com.project.scraper.dynasty;

import java.util.LinkedList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.project.scraper.AScraper;
import com.project.scraper.IScraper;
import com.project.historydatabase.dynasty.*;

public class WikiKingDynasty extends AScraper implements IScraper {
	
	private LinkedList<String> blackList;
	private LinkedList<Dynasty> dynastys;
	
	public WikiKingDynasty() {
		String url = "https://vi.wikipedia.org/wiki/Tri%E1%BB%81u_%C4%91%E1%BA%A1i";
		setUrl(url);
		this.blackList = new LinkedList<String>();
		this.blackList.add("Nhà Lý");
		this.blackList.add("Nhà Trần");
		this.blackList.add("Nhà Hậu Lê");
		this.blackList.add("Nhà Nguyễn");

		connectToUrl();
		this.dynastys = new LinkedList<Dynasty>();
	}	
	
	public LinkedList<Dynasty> getDynastys() {
		return dynastys;
	}

	@Override
	public void scrape() {
		NameOfDynasty names;
		names = new NameOfDynasty();
		names.scrape();
		
		Elements canCrawlNames = this.getDoc()
				.select("#mw-content-text > div.mw-parser-output > ul:nth-child(6) > li > a");
		for (Element e : canCrawlNames) {
			if (names.getDynasty_names().contains(e.text())) {
				if (!this.blackList.contains(e.text())) {
					Dynasty dynasty = new Dynasty(e.text());
					String absHref = e.attr("abs:href");
					WikiPoppedUpPage poppedUp = new WikiPoppedUpPage(absHref);
					poppedUp.scrape();
					dynasty.setKings(poppedUp.getKingNames());
					dynastys.add(dynasty);
					System.out.println(e.text());
				}
			}
		}
	}
}