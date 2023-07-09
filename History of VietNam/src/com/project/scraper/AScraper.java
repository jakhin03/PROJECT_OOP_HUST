package com.project.scraper;

import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.*;

public abstract class AScraper {
	protected String url;
	
	protected Document doc;
	
	// Getter method
	public Document getDoc() {
		return doc;
	}
	// Setter method
	protected void setUrl(String url) {
		this.url = url;
	}
	
	// Constructor for class
	public AScraper( ) {}
	
	protected void connectToUrl() {
		try {
			doc = Jsoup.connect(this.url)
					.timeout(50000)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36")
					.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
