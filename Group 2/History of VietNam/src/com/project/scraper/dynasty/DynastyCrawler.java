package com.project.scraper.dynasty;

import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.historydatabase.dynasty.Dynasty;
import com.project.historydatabase.event.Event;
import com.project.historydatabase.figure.*;
import com.project.scraper.AScraper;
import com.project.scraper.IScraper;
import com.project.scraper.event.IDataToScrape;
import com.project.scraper.event.WikiEvent;

public class DynastyCrawler  extends AScraper implements IScraper, IDataToScrape{
	
	private ArrayList<Dynasty> dynasties = new ArrayList<>();
	private Pattern patternName = Pattern.compile("[A-Za-zÀ-ỹ]+(\\s[A-Za-zÀ-ỹ]+)*");
	
	public ArrayList<Dynasty> getDynasties(){
		return dynasties;
	}
	
	@Override
	public void scrape() {	
		 try {
	            Document doc = Jsoup.connect("http://leloi.phuyen.edu.vn/sinh-hoat-chuyen-mon/to-su-gdcd/lich-su-viet-nam-qua-cac-trieu-dai-2879-tcn-1976-.html").get();
	            Elements rows = doc.select("table tbody tr");
	            Dynasty dynasty = new Dynasty();
	            for (Element row : rows) {
	            	dynasty = new Dynasty();
	            	LinkedList<King> kings = new LinkedList<King>();
	            	Elements cols = row.select("td");	
	            	
	            	//Get dynasty name and kings
	            	Element firstCol = cols.get(0);
	            	Elements dynastyNameAndKings = firstCol.select("p");
	            	String dynastyName = dynastyNameAndKings.get(0).text();
	            	for (int i = 1; i < dynastyNameAndKings.size(); i++) {
	            		String nameUnfiltered = dynastyNameAndKings.get(i).text();
	            		Matcher matcher = patternName.matcher(nameUnfiltered);	
	            		if (matcher.find()){
	            			kings.add(new King(matcher.group(0)));
	            		}
	            	}
	            	if (kings.isEmpty()) {
	            		continue;
	            	}
	            	dynasty.setKings(kings);
	            		            	
	            	//get nam tri vi vua
	            	Element thirdCol = cols.get(2);
	            	Elements listNamTriVi = thirdCol.select("p");
	            	for (int j = 1; j < listNamTriVi.size(); j++) {
	            		String namTriVi = listNamTriVi.get(j).text();
	            		String startYear = namTriVi.split("-")[0].trim();
	            		String endYear = namTriVi.split("-")[1].trim();
	            		kings.get(j).setNamTriVi(namTriVi);
	            	}
	            	dynasty.setStartYear(kings.get(0).getNamTriVi().split("-")[0]);
	            	dynasty.setEndYear(kings.get(kings.size()-1).getNamTriVi().split("-")[1]);
	            	dynasty.setFounder(kings.get(0));
	            }
	            dynasties.add(dynasty);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public static void main(String args[]) throws IOException, InterruptedException {
		DynastyCrawler dynasty = new DynastyCrawler();
		dynasty.scrape();
		String JsonURL = "History%20Data\\dynasty.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(JsonURL));
			ArrayList<Dynasty> dynastiesList = new ArrayList<Dynasty>();
			dynastiesList.addAll(dynasty.getDynasties());
			gson.toJson(dynastiesList, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
