package com.project.scraper;

import com.project.historydatabase.*;
import com.project.historydatabase.dynasty.Dynasty;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataCrawler {
    private WebScraper webScraper;

    public DataCrawler(WebScraper webScraper) {
        this.webScraper = webScraper;
    }

    public void crawlDynastyData() {
        try {
            Document document = webScraper.fetchWebsite("https://example.com/dynasty");

            // Trích xuất dữ liệu từ các phần tử HTML cụ thể
            Elements elements = document.select("CSS Selector");

            // Tạo đối tượng Dynasty từ dữ liệu trích xuất được và thêm vào HistoryDatabase
            for (Element element : elements) {
                // Xử lý dữ liệu và tạo đối tượng Dynasty
                Dynasty dynasty = new Dynasty();
                // Thiết lập thuộc tính cho đối tượng Dynasty

                // Thêm Dynasty vào HistoryDatabase
                webScraper.getHistoryDatabase().addDynasty(dynasty);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crawlHistoricalFigureData() {
        // Tương tự như crawlDynastyData(), nhưng cho nhân vật lịch sử
    }

    public void crawlFestivalData() {
        // Tương tự như crawlDynastyData(), nhưng cho lễ hội
    }
}
