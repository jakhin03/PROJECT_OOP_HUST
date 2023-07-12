package com.project.scraper.figure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.project.scraper.IDataSynthesis;
import com.project.utils.IWriteToJson;
import com.project.historydatabase.figure.Character;


public class OtherWebsiteFigureScrape implements IWriteToJson, IDataSynthesis {
	private ArrayList<Character> list = new ArrayList<Character>();

	public static void main(String[] args) {
		OtherWebsiteFigureScrape otherWebsiteFigureScrape = new OtherWebsiteFigureScrape();
		otherWebsiteFigureScrape.synthesis();
		try {
			otherWebsiteFigureScrape.writeToJson();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void writeToJson() throws JsonIOException, IOException {
		String filePath = "D:\\webCrawler\\webcrawler\\src\\webcrawler\\jsonFiles\\figure.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(list, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void synthesis() {
		Heros heros = new Heros();
		heros.scrape();
		TranDynastyGenerals nhaTran = new TranDynastyGenerals();
		nhaTran.scrape();
		HerosUpdate heros2 = new HerosUpdate();
		heros2.scrape();
		TrangNguyen trangNguyen = new TrangNguyen();
		trangNguyen.scrape();
		list.addAll(heros.getList());
		list.addAll(heros2.getList());
		list.addAll(trangNguyen.getList());
		list.addAll(nhaTran.getList());
	}
}