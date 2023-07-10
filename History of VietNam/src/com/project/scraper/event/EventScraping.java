package com.project.scraper.event;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.project.scraper.IDataSynthesis;
import com.project.utils.IWriteToJson;

import com.project.historydatabase.event.*;
import com.project.scraper.event.WikiEvent;
import com.project.scraper.event.WikiFightsEvent;


public class EventScraping implements IWriteToJson, IDataSynthesis{
	
	private LinkedList<Event> dataList = new LinkedList<Event>();
	

	@Override
	public void synthesis() throws IOException {
		// TODO Auto-generated method stub
		
		WikiEvent events = new WikiEvent();
		events.scrape();
		
		WikiFightsEvent fights = new WikiFightsEvent();
		fights.scrape();
		
		
		dataList.addAll(events.getEvents());
		dataList.addAll(fights.getFightEvents());
		
	}

	@Override
	public void writeToJson() throws JsonIOException, IOException {
		// TODO Auto-generated method stub
		String JsonURL = "History%20Data\\\\dynasty.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(JsonURL));
			gson.toJson(dataList, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		EventScraping eventScraping = new EventScraping();
		eventScraping.synthesis();
		try {
			eventScraping.writeToJson();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
