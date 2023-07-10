package com.project.scraper.relic;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.project.scraper.AScraper;
import com.project.scraper.IScraper;
import com.project.scraper.event.IDataToScrape;
import com.project.historydatabase.relic.*;


public class VHHNScraperOnePage extends AScraper implements IScraper, IDataToScrape {
	private LinkedList<Relic> relics;
	private int lienKetKing = 0;
	private int lienKetDynasty = 0;
	private int lienketCharacter = 0;
	
	public LinkedList<Relic> getRelics() {
		return relics;
	}
	
	public int getLienKetKing() {
		return lienKetKing;
	}



	public int getLienKetDynasty() {
		return lienKetDynasty;
	}



	public int getLienKetCharacter() {
		return lienketCharacter;
	}

	public VHHNScraperOnePage(int i) {
		relics = new LinkedList<Relic>();
		String baseUrl = "http://ditichlichsu-vanhoahanoi.com/category/2dtlsvh/page/";
		String url = baseUrl + Integer.toString(i) + "/";
		this.setUrl(url);
		connectToUrl();
	}

	public void scraping() throws IOException {
		Elements aData = this.getDoc().select("div#post-wrapper > div > article > a");
		System.out.println(aData.text());
		for (Element e: aData) {
			String boxUrl = e.attr("href");
			System.out.println(boxUrl);
			VHHNScraperOneBox h = new VHHNScraperOneBox(boxUrl);
			h.scrape();
			lienKetDynasty += h.getLienKetDynasty();
			lienKetKing += h.getLienKetKing();
			lienketCharacter += h.getlienketCharacter();
			relics.add(h.getRelic());
		}
	}
	
	public static void main(String[] args) throws IOException {
		VHHNScraperOnePage r = new VHHNScraperOnePage(1);
		r.scraping();
	}

	@Override
	public void scrape() throws IOException {
		// TODO Auto-generated method stub
		
	}
}
