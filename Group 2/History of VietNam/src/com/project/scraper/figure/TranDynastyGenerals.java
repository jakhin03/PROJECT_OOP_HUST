package com.project.scraper.figure;

import java.util.ArrayList;
import org.jsoup.nodes.Element;


import org.jsoup.select.Elements;

import com.project.historydatabase.figure.Character;
import com.project.scraper.AScraper;
import com.project.scraper.IScraper;

public class TranDynastyGenerals extends AScraper implements IScraper {
	private ArrayList<Character> list = new ArrayList<Character>();

	public ArrayList<Character> getList() {
		return this.list;
	}

	public TranDynastyGenerals() {
		String url = "http://www.quan8.hochiminhcity.gov.vn/dantaphaibietsuta/lists/posts/post.aspx?Source=/dantaphaibietsuta&Category=Nh%C3%A2n+v%E1%BA%ADt+l%E1%BB%8Bch+s%E1%BB%AD+t%E1%BB%AB+th%E1%BA%BF+k%E1%BB%B7+X+%C4%91%E1%BA%BFn+XV&ItemID=83&Mode=1";
		setUrl(url);
		connectToUrl();
	}

	@Override
	public void scrape() {
		Element mainDiv = this.doc.getElementById("part1");
		Element mainTable = mainDiv.getElementsByTag("table").first();
		Element paragraphs = mainTable.select("div.ExternalClass6BD0A3317E7F4013AACBBE0FE404A173").first();
		Elements figureParagraph = paragraphs.select("p:has(b)");
		for (Element p : figureParagraph) {
			Elements header = p.getElementsByTag("b");
			String ten = header.text();
			if (!ten.equals("")) {
				int index = ten.indexOf(" ");
				ten = ten.substring(index);
				System.out.println(ten);
				if (ten.contains("(")) {
					index = ten.indexOf("(");
					String nam = ten.substring(index);
					ten = ten.substring(0, index);
					nam = nam.replace("(", "");
					nam = nam.replace(")", "");
					index = nam.indexOf("-");
					String namSinh = nam.substring(0, index);
					String namMat = nam.substring(index + 1);
					System.out.println(namSinh + "+" + namMat);
					Character character = new Character(ten, namSinh, namMat);
					list.add(character);
				} else {
					System.out.println(ten);
					Character character = new Character(ten);
					list.add(character);
				}
			}
		}

	}


}