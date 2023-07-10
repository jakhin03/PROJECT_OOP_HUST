package com.project.scraper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import objects.dynasty.Dynasty;
import objects.figure.Figure;
import objects.figure.King;
import objects.relic.Relic;
import webcrawler.combine.ICombineData;
import webcrawler.linkdata.LinkRelicWithFigureAndDynasty;
import webcrawler.tojson.IWriteJson;


public class RelicCrawler  {
	public void combine() throws IOException {
        LinkRelicWithFigureAndDynasty linkRelic = new LinkRelicWithFigureAndDynasty();

        String baseUrl = "http://ditich.vn/FrontEnd/DiTich/Form?do=&ItemId="; // 6193 - 1865
        for (int i = 1865; i <= 6139; i++) {

            String url = baseUrl + Integer.toString(i);
            RelicScrapeDiTichOnePage r = new RelicScrapeDiTichOnePage(url);
            r.scraping();
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
                LinkedList<Figure> figures = linkRelic.getFigures();
                LinkedList<King> kings = linkRelic.getKings();
                LinkedList<Dynasty> dynastys = linkRelic.getDynastys();

                lienKetDynasty += linkRelic.getLienKetDynasty();
                lienKetKing += linkRelic.getLienKetKing();
                lienKetFigure += linkRelic.getLienKetFigure();

                Relic r1 = new Relic(r.getName(), r.getAddress(), r.getType(), r.getRank(), tenNguoiTho, figures, kings,
                        dynastys);
                relics.add(r1);
            }

        }
        System.out.println(lienKetDynasty);
        System.out.println(lienKetFigure);
        System.out.println(lienKetKing);
    }
}
