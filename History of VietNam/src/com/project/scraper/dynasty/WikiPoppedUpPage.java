package com.project.scraper.dynasty;

import java.util.LinkedList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.project.historydatabase.figure.King;
import com.project.scraper.AScraper;
import com.project.scraper.IScraper;

public class WikiPoppedUpPage extends  AScraper implements IScraper {
	private LinkedList<King> kingNames;

	public void scrape() {
		Elements kings = this.getDoc().select("#mw-content-text > div.mw-parser-output > table.infobox > tbody > tr");
		boolean flag = false;
		int cntFlag = 0;
		for (Element e : kings) {
			Elements aData = e.getElementsByTag("a");
			Elements tdData = e.getElementsByTag("td");
			Elements thData = e.getElementsByTag("th");
			
			if ((aData.size() > 0 && (aData.text().equals("Hoàng đế") || aData.text().equals("Vua")))
					|| thData.text().equals("Hoàng Đế")) {
		
				flag = true;
				continue;
			}
			if (flag && tdData.text().equals("")) {
				cntFlag += 1;
				if (cntFlag == 2) {
					flag = false;
				}

			}
			if (aData.size() > 0 && flag && !aData.get(aData.size() - 1).text().equals("")
					&& !aData.get(aData.size() - 1).text().matches(".*[0-9].*")) {
				kingNames.add(new King(aData.get(aData.size() - 1).text()));
			}
		}
	}

	public WikiPoppedUpPage(String url) {
		this.kingNames = new LinkedList<King>();
		setUrl(url);
		connectToUrl();
	}

	public LinkedList<King> getKingNames() {
		return kingNames;
	}
}