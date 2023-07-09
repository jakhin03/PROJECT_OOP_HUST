package com.project.scraper.event;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.project.scraper.AScraper;
import com.project.scraper.IScraper;
import com.project.utils.HasNumber;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.historydatabase.event.*;

public class WikiFightsEvent extends AScraper implements IScraper, IDataToScrape{

	private ArrayList<Event> WikiFights = new ArrayList<Event>();
	
	public ArrayList<Event> getFightEvents(){
		return WikiFights;
	}
	public WikiFightsEvent() {
		String baseUrl = "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_tr%E1%BA%ADn_%C4%91%C3%A1nh_trong_l%E1%BB%8Bch_s%E1%BB%AD_Vi%E1%BB%87t_Nam";
		setUrl(baseUrl);
		connectToUrl();
	}
	 
	@Override
	public void scrape() throws IOException {
	    String strTime = "";
	    Element mainPage = this.doc.getElementById("bodyContent");
	    Elements fightEvents = mainPage.select("#mw-content-text > div.mw-parser-output > ul > li");
	    
	    for (Element fight : fightEvents) {
	        String name = fight.text();
//	        name = name.replaceAll("\\(.*?\\)|\\)", "");
	        
	        
	        String tempStrTime = scrapeTime(name);
	        if (tempStrTime.length() != 0) {
	            strTime = tempStrTime;
	        } 
	        name = name.replaceAll(" - ", "-");
	        if (name.contains("-")) {
	        	Pattern pattern = Pattern.compile("(\\d+)-(\\d+)");
	            Matcher matcher = pattern.matcher(name);
                if (matcher.find()) {
                    // extract the start and end years from the matched groups
                    String startYear = matcher.group(1);
                    String endYear = matcher.group(2);
                    strTime = startYear + "-" + endYear;
                }
	        }
	        strTime.replaceAll("#", "");
	        strTime = strTime.replaceAll("#", "");
	        String destination = scrapeDestination(name, strTime);
	        String relatedFigure = scrapeFigure(name, strTime);
	        if (name.contains("1988")) break;
	        Event e = new Event();
	        if (name.equals("Dẹp Loạn 12 sứ quân")) {
	        	e.setTen(name);
	        }
	        else e.setTen(name.replaceAll(",\\s*\\d+|\\s*\\(\\d+-\\d+\\)|\\(\\d+\\)|-\\d+|\\(\\d+\\s*-\\s*\\d+\\)|\\.|\\s*\\d+", "").trim());
	        
	        e.setThoiGian(strTime);
	        e.setDiaDiem(destination.trim());
	        e.setNhanVatLienQuan(relatedFigure);
	        
	        WikiFights.add(e);
	    }
	}
	
	public static void main(String args[]) throws IOException, InterruptedException {
		WikiFightsEvent fights = new WikiFightsEvent();
		fights.scrape();
		String JsonURL = "src\\test1.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(JsonURL));
			ArrayList<Event> DanhSachSuKien = new ArrayList<Event>();
			DanhSachSuKien.addAll(fights.getFightEvents());
			gson.toJson(DanhSachSuKien, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
