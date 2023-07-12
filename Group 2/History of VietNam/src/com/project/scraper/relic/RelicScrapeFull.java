package com.project.scraper.relic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.project.historydatabase.relic.Relic;
import com.project.scraper.IDataSynthesis;
import com.project.utils.IWriteToJson;



public class RelicScrapeFull  implements IWriteToJson, IDataSynthesis {
	private LinkedList<Relic> relics;

	public RelicScrapeFull() {
		relics = new LinkedList<Relic>();
	}

	@Override
	public void synthesis() throws IOException {
		RelicDiTichCrawler r_d = new RelicDiTichCrawler();
		r_d.synthesis();
		relics.addAll(r_d.getRelics());

		VHHNScraper r_h = new VHHNScraper();
		r_h.synthesis();
		relics.addAll(r_h.getRelics());
		System.out.println(r_h.getLienKetDynasty());
		System.out.println(r_h.getlienKetCharater());
		System.out.println(r_h.getLienKetKing());
	}

	public void writeJSon() throws JsonIOException, IOException {
		String filePath = "D:\\HistoryVietnam\\FestivalJSon\\jsonrelic.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(relics, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) throws IOException {
		RelicScrapeFull r_f = new RelicScrapeFull();
		r_f.synthesis();
		r_f.writeJSon();
	}


	@Override
	public void writeToJson() throws JsonIOException, IOException {
		// TODO Auto-generated method stub
		
	}

}
