package com.project.scraper.figure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.project.scraper.AScraper;
import com.project.scraper.IScraper;
import com.project.utils.IWriteToJson;
import com.project.historydatabase.figure.King;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class KingScrape extends AScraper implements IScraper, IWriteToJson {
	private ArrayList<King> kings = new ArrayList<King>();

	public KingScrape() {
		String url = "https://vi.wikipedia.org/wiki/Vua_Vi%E1%BB%87t_Nam";
		this.setUrl(url);
		connectToUrl();
	}

	public Document getDoc() {
		return this.doc;
	}

	public ArrayList<King> getKings() {
		return kings;
	}

	public void setKings(ArrayList<King> kings) {
		this.kings = kings;
	}

	@Override
	public void scrape() {
		Elements firstTable = this.doc.select("table.toccolours");
		Elements tables = doc.select("table");
		for (Element t : firstTable) {
			if (tables.contains(t)) {
				tables.remove(t);
			}
		}
		int lastTable = tables.size() - 1;
		tables.remove(lastTable);
		String strRegEx = "<sup[^?]*";
		String attrRegEx = "<a[^?]*";
		for (Element table : tables) {
			Elements rows = table.select("tr");
			for (Element row : rows) {
				Elements datas = row.getElementsByTag("td");
				if (datas.size() >= 10 && datas.size() < 18) {
					String name = datas.get(1).text();
					name = cleanData(name);
					// name = name.replaceAll(openRegEx, "");
					King king = new King(name);
					Element attr = datas.get(1).select("a").first();
					if (attr != null) {
						String url = attr.absUrl("href");
						System.out.println(url);
						king.setArticleLink(url);
					}

					String mieuHieu = datas.get(2).text();
					mieuHieu = cleanData(mieuHieu);
					king.setMieuHieu(mieuHieu);
					String thuyHieu = datas.get(3).text();
					thuyHieu = cleanData(thuyHieu);
					king.setThuyHieu(thuyHieu);
					String nienHieu = datas.get(4).text();
					nienHieu = cleanData(nienHieu);
					king.setNienHieu(nienHieu);
					String tenHuy = datas.get(5).text();
					tenHuy = cleanData(tenHuy);
					king.setTenHuy(tenHuy);
					String theThu = datas.get(6).text();
					theThu = cleanData(theThu);
					king.setTheThu(theThu);
					String namTriVi = datas.get(7).html();
					namTriVi = namTriVi.replaceAll(strRegEx, "");
					String ngang = datas.get(8).text();
					String end = datas.get(9).html();
					end = end.replaceAll(strRegEx, "");
					namTriVi = namTriVi.concat(ngang);
					namTriVi = namTriVi.concat(end);
					namTriVi = namTriVi.replaceAll(attrRegEx, "");
					namTriVi = cleanData(namTriVi);
					king.setNamTriVi(namTriVi);
					if (!mieuHieu.equals("")) {
						kings.add(king);
					}
				} else
					continue;
			}
		}
	}

	public String cleanData(String sample) {
		String data = new String(sample);
		int index = data.indexOf("[");
		while (index != -1) {
			int close = data.indexOf("]");
			String tmp = data.substring(index, close + 1);
			data = data.replace(tmp, "");
			index = data.indexOf("[");
		}
		return data;
	}

	public static void main(String[] args) throws JsonIOException, IOException {
		KingScrape obj = new KingScrape();
		obj.scrape();
		obj.writeToJson();
	
	}

	@Override
	public void writeToJson() throws JsonIOException, IOException {
		String filePath = "D:\\webCrawler\\jSoupWebCrawler\\src\\data\\king.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(kings, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}