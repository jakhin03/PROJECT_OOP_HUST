package com.project.scraper.relic;

import java.io.IOException;
import java.util.LinkedList;

import com.project.scraper.AScraper;
import com.project.scraper.IScraper;
import com.project.scraper.datalinking.LinkRelicWithCharacterAndDynasty;
import com.project.historydatabase.relic.*;
import com.project.historydatabase.dynasty.Dynasty;
import com.project.historydatabase.figure.*;
import com.project.historydatabase.figure.Character;



public class VHHNScraperOneBox extends AScraper implements IScraper {
	private Relic relic;
	private LinkRelicWithCharacterAndDynasty linkRelic;
	private int lienKetKing = 0;
	private int lienKetDynasty = 0;
	private int lienketCharacter = 0;
	
	public Relic getRelic() {
		return relic;
	}
	

	public int getLienKetKing() {
		return lienKetKing;
	}



	public int getLienKetDynasty() {
		return lienKetDynasty;
	}



	public int getlienketCharacter() {
		return lienketCharacter;
	}



	public VHHNScraperOneBox(String url) throws IOException {
		this.setUrl(url);
		connectToUrl();
		linkRelic = new LinkRelicWithCharacterAndDynasty();
	}
	@Override
	public void scrape() {
		
		String title = this.getDoc().select("header > h1").text();
		String name = title.split(" \\(")[0];
		String location;
		String type;
		String desc = this.getDoc().select(".entry-content").text();
		
		if (title.contains("La Khê")) {
			location = "Hà Đông";
		} else if (title.split(" \\(").length > 1) {
			location = title.split(" \\(")[1].replace(")", "");
		} else {
			location = "Hà Nội";
		}
		if (name.contains("Đình")) {
			type = "Đình";
		} else if (name.contains("Chùa")) {
			type = "Chùa";
		} else if (name.contains("Đền")) {
			type = "Đền";
		} else if (name.contains("Miếu")) {
			type = "Miếu";
		} else {
			type = "Nghè";
		}
		
		linkRelic.genLink(desc);
		lienKetDynasty += linkRelic.getLienKetDynasty();
		lienKetKing += linkRelic.getLienKetKing();
		lienketCharacter += linkRelic.getLienKetCharacter();
		LinkedList<Character> Characters = linkRelic.getCharacters();
		LinkedList<King> kings = linkRelic.getKings();
		LinkedList<Dynasty> dynastys = linkRelic.getDynastys();
		
		relic = new Relic(name, location, type
				, "Unknown", desc, Characters, kings, dynastys);
		System.out.println(name);
		System.out.println(location);
		System.out.println(type);
		System.out.println(desc);

	}

}
