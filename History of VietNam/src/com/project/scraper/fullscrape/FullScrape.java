package com.project.scraper.fullscrape;

import java.io.IOException;

import com.google.gson.JsonIOException;
import com.project.scraper.IDataSynthesis;
import com.project.scraper.dynasty.FullDynasty;
import com.project.scraper.event.EventScraping;
import com.project.scraper.figure.KingScrape;


public class FullScrape implements IDataSynthesis {
	private FullDynasty dynasty; 
	private RelicScrapeFull relic;
	private KingScrape king;
	private com.project.scraper.figure.FullScrape figure;
	private EventScraping event;
	
	public ScrapeFull() throws JsonIOException, IOException {
		king = new KingScrape();
		relic = new RelicScrapeFull();
		figure = new com.project.scraper.figure.FullScrape();
		event = new EventScraping();
		dynasty = new FullDynasty();		
	}

	@Override
	public void synthesis() throws IOException {
		king.scrape();
		king.writeToJson();
		
		figure.synthesis();
		figure.writeToJson();
		
		dynasty.synthesis();
		dynasty.writeToJson();
		
//		relic.synthesis();
//		relic.writeJSon();
//		
		event.synthesis();
		event.writeToJson();
	}
	
//	public static void main(String[] args) throws JsonIOException, IOException {
//		ScrapeFull full = new ScrapeFull();
//		full.combine();
//	}
}