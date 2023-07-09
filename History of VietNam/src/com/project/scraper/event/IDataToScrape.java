package com.project.scraper.event;

import java.util.ArrayList;
import java.util.Arrays;

public interface IDataToScrape {
	
	default String scrapeName(String data) {
		return "";
	}
	
	default String scrapeTime(String data) {
		data = data.replaceAll("[^0-9]", "#");
		String[] arrData = data.split("#");
		
		boolean isYear = false;
		int numYear = 0;
		StringBuilder years = new StringBuilder();

		for (String s : arrData) {
			if (s.matches("[0-9]{3}$") || s.matches("[0-9]{4}$"))
				isYear = true;
			numYear++;
			if (numYear > 1)
				years.append(" - ");
			years.append(s);
		}
		if (!isYear) return "";
			return data.toString();
	}
	
	
	default String scrapeDestination(String data, String strToDelete) {
		final ArrayList<String> keywords = new ArrayList<>(Arrays.asList("Trận ", "Chiến dịch ", "Biến cố ", "Chiến tranh ", "Văn hóa "));
		boolean needEdit = false;
		data = data.replace(strToDelete, "");

		for (String keyword : keywords) {
			if (data.contains(keyword)) {
				needEdit = true;
				data = data.replace(keyword, "");
				data = data.replaceAll("[-()]", "");
			}
		}
		if (needEdit)
			return data;
		return "";
	}
	
	default String scrapeFigure(String data, String strToDelete) {
		if (data.contains("Dẹp Loạn")) {
			data = data.replace("Dẹp Loạn ", "");
			data = data.replace(strToDelete, "");
			return data;
		}
		return "";
	}
}

	
