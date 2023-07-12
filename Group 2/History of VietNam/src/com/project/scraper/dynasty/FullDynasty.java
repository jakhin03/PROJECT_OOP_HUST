package com.project.scraper.dynasty;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.project.scraper.IDataSynthesis;
import com.project.utils.IWriteToJson;
import com.project.historydatabase.figure.King;
import com.project.historydatabase.dynasty.Dynasty;


public class FullDynasty implements IDataSynthesis, IWriteToJson {
	private NameOfDynasty crawlNames;
	private WikiFounderDynasty crawlFounder;
	private WikiKingDynasty firstKings;
	private LinkedList<Dynasty> dynastys;
	private NKSDynastiesKing remainedKings;

	public FullDynasty() {
		dynastys = new LinkedList<Dynasty>();
	}
	
	@Override
	public void writeToJson() throws JsonIOException, IOException {
		String filePath = "\\data\\dynasty.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(dynastys, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void synthesis() throws IOException {
		firstKings = new WikiKingDynasty();
		firstKings.scrape();

		crawlNames = new NameOfDynasty();
		crawlNames.scrape();

		crawlFounder = new WikiFounderDynasty();
		crawlFounder.scrape();

		remainedKings = new NKSDynastiesKing();
		remainedKings.scrape();

		LinkedList<String> dynastyNames = crawlNames.getDynasty_names();

		for (String name : dynastyNames) {
			// System.out.println(name);
			Dynasty d = new Dynasty(name);
			dynastys.add(d);
		}
		firstKings.getDynastys().addAll(remainedKings.getDynastys());
		for (Dynasty d_1 : firstKings.getDynastys()) {
			for (Dynasty d_2 : dynastys) {
				if (d_1.getName().equals(d_2.getName())) {
					// System.out.println("* " + d_1.getName());
					d_2.setKings(d_1.getKings());
				}
			}
		}

		for (Dynasty d_1 : crawlFounder.getDynastys()) {
			for (Dynasty d_2 : dynastys) {
				if (d_1.getName().equals(d_2.getName())) {
					d_2.setFounder(d_1.getFounder());
					System.out.println("* " + d_2.getName() + "-" + d_2.getFounder());
				}
			}
		}

		for (Dynasty d_1 : dynastys) {
			if (d_1.getFounder() == null) {
				d_1.setFounder(new King("Unknown"));
			}
			System.out.println("* " + d_1.getName() + "-" + d_1.getFounder());
		}

		for (Dynasty d : dynastys) {
			if (d.getKings() == null) {
				WikiWandKings d_w = new WikiWandKings(d.getName());
				d_w.scrape();

				System.out.println("** " + d.getName());
				System.out.println(d_w.getKings().size());

				d.setKings(d_w.getKings());
			}

			WikiYearDynasty d_y = new WikiYearDynasty(d.getName());
			
			d.setStartYear(d_y.getBeginYear());
			d.setEndYear(d_y.getEndYear());

			WikiCapitalDynasty d_c = new WikiCapitalDynasty(d.getName());
			d_c.scrape();

			d.setCapital(d_c.getCapital());
		}

	}
}