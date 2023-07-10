package com.project.scraper.figure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.project.scraper.AScraper;
import com.project.scraper.IDataSynthesis;
import com.project.scraper.IScraper;
import com.project.utils.IWriteToJson;
import com.project.historydatabase.dynasty.Dynasty;
import com.project.historydatabase.figure.Character;
import java.lang.reflect.Type;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FullScrape extends AScraper implements IWriteToJson, IDataSynthesis {
	private LinkedList<Character> list = new LinkedList<Character>();

	public static void main(String[] args) {
		FullScrape figure = new FullScrape();
		figure.synthesis();
		try {
			figure.writeToJson();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String replaceTrieuDai(String original) {
		String trieuDai = "";
		switch (original) {
			case "Bắc thuộc lần 1": {
				trieuDai = trieuDai.concat("Nhà Triệu");
				break;
			}
			case "Trưng Nữ Vương": {
				trieuDai = trieuDai.concat("Hai Bà Trưng");
				break;
			}
			case "Nhà Tiền Lý, Triệu": {
				trieuDai = trieuDai.concat("Nhà Tiền Lý");
				break;
			}
			case "Hậu Trần": {
				trieuDai = trieuDai.concat("Nhà Hậu Trần");
				break;
			}
			case "Trịnh - Nguyễn": {
				trieuDai = trieuDai.concat("Nhà Hậu Lê");
				break;
			}
			case "Triều Lê Sơ": {
				trieuDai = trieuDai.concat("Nhà Lê sơ");
				break;
			}
			case "Nam - Bắc Triều": {
				trieuDai = trieuDai.concat("Nhà Mạc");
				break;
			}
			case "Nhà Nguyễn độc lập": {
				trieuDai = trieuDai.concat("Nhà Nguyễn");
				break;
			}
			case "Pháp đô hộ": {
				trieuDai = trieuDai.concat("Đế quốc Việt Nam");
				break;
			}
			case "Nước Việt Nam mới": {
				trieuDai = trieuDai.concat("Việt Nam Dân chủ Cộng hòa");
				break;
			}
			case "Dựng nước": {
				trieuDai = trieuDai.concat("Hồng Bàng thị");
				break;
			}
			default: {
				trieuDai = trieuDai.concat(original);
			}
		}
		return trieuDai;
	}

	public static JSONArray readData(String path) {
		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();
		JSONArray dataList = null;
		try (FileReader reader = new FileReader(path)) {
			// Read JSON file
			Object obj = jsonParser.parse(reader);
			dataList = (JSONArray) obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataList;

	}

	@Override
	public void synthesis() {
		int pageIndex = 1;
		String urlFirstHalf = "https://vansu.vn/viet-nam/viet-nam-nhan-vat/";

		while (pageIndex <= 2300) {
			String url = urlFirstHalf + Integer.toString(pageIndex);
			VanSuFigure vanSu = new VanSuFigure(url);
			vanSu.scrape();
			list.add(vanSu.getFigure());
			pageIndex += 1;
		}

		System.out.println("num of mem: " + list.size());
		for (Character character : list) {
			LinkedList<Dynasty> dynastyList = character.getTrieuDai();
			for (Dynasty dynasty : dynastyList) {
				String name = dynasty.getName();
				dynasty.setName(replaceTrieuDai(name));
			}
		}
	}

	@Override
	public void writeToJson() throws JsonIOException, IOException {
		String filePath = "src\\figureUpdate.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(list, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}