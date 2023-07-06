package com.project.scraper;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.project.historydatabase.HistoryDatabase;

public class WebScraper {
    private HttpClient httpClient;
    private HistoryDatabase historyDatabase;

    public WebScraper() {
        this.httpClient = HttpClientBuilder.create().build();
    }

    public Document fetchWebsite(String url) throws Exception {
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);

        String html = EntityUtils.toString(response.getEntity());
        return Jsoup.parse(html);
    }

    public HistoryDatabase getHistoryDatabase() {
        return historyDatabase;
    }
}
