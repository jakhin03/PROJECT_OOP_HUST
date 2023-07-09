package com.project.scraper;

import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.historydatabase.dynasty.Dynasty;
import com.project.historydatabase.figure.*;

public class DynastyCrawler implements IScraper{
	
	private ArrayList<Dynasty> dynasties = new ArrayList<>();
	private Pattern patternName = Pattern.compile("[A-Za-zÀ-ỹ]+(\\s[A-Za-zÀ-ỹ]+)*");

	public void scrape() {	
		 try {
	            Document doc = Jsoup.connect("http://leloi.phuyen.edu.vn/sinh-hoat-chuyen-mon/to-su-gdcd/lich-su-viet-nam-qua-cac-trieu-dai-2879-tcn-1976-.html").get();
	            Elements rows = doc.select("table tbody tr");
	            for (Element row : rows) {
	            	Dynasty dynasty = new Dynasty();
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
	            	dynasty.setKings(kings);	            	          
	            	
	            	//get nam tri vi vua
	            	Element thirdCol = cols.get(2);
	            	Elements listNamTriVi = thirdCol.select("p");
	            	for (int j = 1; j < listNamTriVi.size(); j++) {
	            		String namTriVi = listNamTriVi.get(j).text();
	            		String startYear = namTriVi.split("-")[0].trim();
//	            		String endYear = namTriVi.split("-")[1].trim();
	            		System.out.println(startYear);
	            	}
	            }
	            
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	
}
