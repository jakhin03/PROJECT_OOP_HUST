package com.project.scraper.event;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.project.scraper.AScraper;
import com.project.scraper.IScraper;
import com.project.utils.HasNumber;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.historydatabase.event.*;

public class WikiEvent extends AScraper implements IScraper, IDataToScrape{

	private ArrayList<Event> WikiEvent = new ArrayList<Event>();
	
	public ArrayList<Event> getEvents(){
		return WikiEvent;
	}
	public WikiEvent() {
		String baseUrl = "https://vi.wikipedia.org/wiki/Ni%C3%AAn_bi%E1%BB%83u_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
		setUrl(baseUrl);
		connectToUrl();
	}
	 
	@Override
	public void scrape() throws IOException {
		String tempStrTime = "";
		Element mainPage = this.doc.getElementById("bodyContent");
		Elements events = mainPage.select("#mw-content-text > div.mw-parser-output > p, dd");

		for (Element event : events) {
			String name = event.select("a").text();
			if (name.length() == 0) {
				tempStrTime = event.select("b").text();
				tempStrTime = HasNumber.hasNumber(tempStrTime);
				continue;
			}
			String strTime = event.select("b").text();
			if (HasNumber.hasNumber(strTime) == "")
				continue;
			if (scrapeTime(strTime) == "")
				strTime += (" " + tempStrTime);
			String destination = scrapeDestination(name, strTime);
			String relatedFigure = scrapeFigure(name, strTime);
			Event e = new Event();
			e.setTen(name);
			e.setThoiGian(strTime);
			e.setDiaDiem(destination);
			e.setNhanVatLienQuan(relatedFigure);
			
			WikiEvent.add(e);
		}
		
	}
	
	public static void main(String args[]) throws IOException, InterruptedException {
		WikiEvent sukien = new WikiEvent();
		sukien.scrape();
		String JsonURL = "src\\test.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(JsonURL));
			ArrayList<Event> DanhSachSuKien = new ArrayList<Event>();
			DanhSachSuKien.addAll(sukien.getEvents());
			gson.toJson(DanhSachSuKien, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
