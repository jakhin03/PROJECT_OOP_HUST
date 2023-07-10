package com.project.scraper.relic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.project.scraper.IDataSynthesis;
import com.project.utils.IWriteToJson;

import com.project.historydatabase.relic.Relic;
import com.project.historydatabase.figure.Character;
import com.project.historydatabase.figure.King;
import com.project.historydatabase.dynasty.Dynasty;
import com.project.scraper.datalinking.*;

public class RelicDiTichCrawler implements IWriteToJson, IDataSynthesis {

	private LinkedList<Relic> relics;
	private int lienKetKing = 0;
	private int lienKetDynasty = 0;
	private int lienKetCharacter = 0;
	
	public RelicDiTichCrawler() throws IOException {
		relics = new LinkedList<Relic>();
	}

	

	public int getLienKetKing() {
		return lienKetKing;
	}



	public int getLienKetDynasty() {
		return lienKetDynasty;
	}



	public int lienKetCharacter() {
		return lienKetCharacter;
	}


	@Override
	public void writeToJson() throws JsonIOException, IOException {
		String filePath = "D:\\relic_new.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(relics, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) throws JsonIOException, IOException {
		RelicDiTichCrawler relicScrape = new RelicDiTichCrawler();
		relicScrape.synthesis();
		relicScrape.writeToJson();
	}

	public LinkedList<Relic> getRelics() {
		return relics;
	}

	@Override
	public void synthesis() throws IOException {
		LinkRelicWithFigureAndDynasty linkRelic = new LinkRelicWithFigureAndDynasty();

		String baseUrl = "http://ditich.vn/FrontEnd/DiTich/Form?do=&ItemId="; 
		for (int i = 1865; i <= 6139; i++) {

			String url = baseUrl + Integer.toString(i);
			RelicDiTichPageCrawler r = new RelicDiTichPageCrawler(url);
			r.scrape();
			if (r.getName().strip() != "") {
				System.out.println(i);
				System.out.println(r.getName());
				System.out.println(r.getAddress());
				System.out.println(r.getPerson());
				String tenNguoiTho = r.getPerson();
				
				linkRelic.setLienKetDynasty(0);
				linkRelic.setLienKetFigure(0);
				linkRelic.setLienKetKing(0);
				
				linkRelic.genLink(tenNguoiTho);
				LinkedList<Character> characters = linkRelic.getFigures();
				LinkedList<King> kings = linkRelic.getKings();
				LinkedList<Dynasty> dynastys = linkRelic.getDynastys();

				lienKetDynasty += linkRelic.getLienKetDynasty();
				lienKetKing += linkRelic.getLienKetKing();
				lienKetCharacter += linkRelic.getLienKetCharacter();

				Relic r1 = new Relic(r.getName(), r.getAddress(), r.getType(), r.getRank(), tenNguoiTho, characters, kings,
						dynastys);
				relics.add(r1);
			}

		}
		System.out.println(lienKetDynasty);
		System.out.println(lienKetCharacter);
		System.out.println(lienKetKing);
	}
}