package com.project.scraper.relic;

import java.io.IOException;
import java.util.LinkedList;

import com.project.scraper.AScraper;
import com.project.scraper.IScraper;
import com.project.scraper.IDataSynthesis;
import com.project.historydatabase.relic.*;


public class VHHNScraper extends AScraper implements IScraper, IDataSynthesis {
	
	private LinkedList<Relic> relics;
	private int lienKetKing = 0;
	private int lienKetDynasty = 0;
	private int lienKetCharater = 0;
	
	public VHHNScraper() throws IOException {
		relics = new LinkedList<Relic>();
	}
	
	
	
	public int getLienKetKing() {
		return lienKetKing;
	}



	public int getLienKetDynasty() {
		return lienKetDynasty;
	}



	public int getlienKetCharater() {
		return lienKetCharater;
	}



	public LinkedList<Relic> getRelics() {
		return relics;
	}
	
	public static void main(String[] args) throws IOException {
		VHHNScraper r = new VHHNScraper();
		r.synthesis();
	}

	@Override
	public void synthesis() throws IOException {
		int cnt = 0;
		for (int i=1;i<=44;i++) {
			VHHNScraperOnePage r_one = new VHHNScraperOnePage(i);
			r_one.scrape();
			cnt += r_one.getRelics().size();
			lienKetDynasty += r_one.getLienKetDynasty();
			lienKetKing += r_one.getLienKetKing();
			lienKetCharater += r_one.getLienKetCharacter();
			relics.addAll(r_one.getRelics());
		}
		System.out.println(cnt);
		System.out.println(lienKetDynasty);
		System.out.println(lienKetCharater);
		System.out.println(lienKetKing);
	}



	@Override
	public void scrape() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
