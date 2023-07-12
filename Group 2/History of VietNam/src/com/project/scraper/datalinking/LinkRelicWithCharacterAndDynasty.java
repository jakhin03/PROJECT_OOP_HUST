package com.project.scraper.datalinking;

import java.io.IOException;
import java.util.LinkedList;
import com.project.historydatabase.figure.Character;
import com.project.historydatabase.figure.King;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.project.historydatabase.dynasty.Dynasty;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LinkRelicWithCharacterAndDynasty extends DataLinking {
	private LinkedList<Character> characters;
	private LinkedList<King> kings;
	private LinkedList<Dynasty> dynastys;
	private LinkedList<String> added;
	private int lienKetKing = 0;
	private int lienKetDynasty = 0;
	private int lienKetCharacter = 0;
	public class ReadData<T> {

	    public ObservableList<T> FromJsonToArray(String filePath, Class<T> objectType) throws IOException {
	        // String carInfoJson = new String(Files.readAllBytes(Paths.get(filePath)));
	        Gson gson = new Gson();
	        List<T> objects = null;
	        Type listType = TypeToken.getParameterized(List.class, objectType).getType();
	        objects = gson.fromJson(new FileReader(filePath), listType);
	        ObservableList<T> result = FXCollections.observableList(objects);
	        return result;
	    }
	}
	public LinkRelicWithCharacterAndDynasty() throws IOException {
		listObservablesCharacter = new ReadData<Character>()
				.FromJsonToArray("src/data/figureUpdate.json", Character.class);

		listObservablesKing = new ReadData<King>().FromJsonToArray("src/data/king.json",
				King.class);
	}
	
	
	@Override
	public void genLink(String tenNguoiTho) {
		this.characters = new LinkedList<Character>();
		this.kings = new LinkedList<King>();
		this.dynastys = new LinkedList<Dynasty>();
		this.added = new LinkedList<String>();
		for (King f : listObservablesKing) {
			if (f.getTen() != null) {
				if (f.getTenHuy() == null && f.getThuyHieu() == null) {
					if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())) {
						kings.add(new King(f.getTen()));
					}
				} else if (f.getTenHuy() == null && f.getThuyHieu() != null) {
					if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
							|| tenNguoiTho.toLowerCase().contains(f.getThuyHieu().toLowerCase())) {
						kings.add(new King(f.getTen()));
					}
				} else if (f.getTenHuy() != null && f.getThuyHieu() == null) {
					if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
							|| tenNguoiTho.toLowerCase().contains(f.getTenHuy().toLowerCase())) {
						kings.add(new King(f.getTen()));
					}
				} else {
					if (tenNguoiTho.toLowerCase().contains(f.getTen().toLowerCase())
							|| tenNguoiTho.toLowerCase().contains(f.getTenHuy().toLowerCase())
							|| tenNguoiTho.toLowerCase().contains(f.getThuyHieu().toLowerCase())) {
						kings.add(new King(f.getTen()));
					}
				}
			}

		}

		for (Character c : listObservablesCharacter) {
			if (c.getTenKhac() == null) {
				if (tenNguoiTho.toLowerCase().contains(c.getTen().toLowerCase())) {
					characters.add(new Character(c.getTen()));
					for (Dynasty d : c.getTrieuDai()) {
						if (!added.contains(d.getName())) {
							added.add(d.getName());
							dynastys.add(new Dynasty(d.getName()));
						}
					}
				}
			} else if (tenNguoiTho.toLowerCase().contains(c.getTen().toLowerCase())
					|| tenNguoiTho.toLowerCase().contains(c.getTenKhac().toLowerCase())) {
				characters.add(new Character(c.getTen()));
				for (Dynasty d : c.getTrieuDai()) {
					if (!added.contains(d.getName())) {
						added.add(d.getName());
						dynastys.add(new Dynasty(d.getName()));
					}
				}
			}

		}
		
		if (characters.size() != 0) {
			lienKetCharacter += characters.size();
			System.out.println("Found!!!!!!");
			for (int j=0;j<characters.size();j++) {
				System.out.println(characters.get(j).getTen());
			}
		}
		
		if (kings.size() != 0) {
			lienKetKing += kings.size();
			System.out.println("Found King!!!!!!");
			for (int j=0;j<kings.size();j++) {
				System.out.println(kings.get(j).getTen());
			}
		}
		
		if (dynastys.size() != 0) {
			lienKetDynasty += dynastys.size();
			System.out.println("Found D!!!!!!");
			for (int j=0;j<dynastys.size();j++) {
				System.out.println(dynastys.get(j).getName());
			}
		}
		
	}
	
	

	public void setLienKetKing(int lienKetKing) {
		this.lienKetKing = lienKetKing;
	}

	public void setLienKetDynasty(int lienKetDynasty) {
		this.lienKetDynasty = lienKetDynasty;
	}

	public void setLienKetFigure(int lienKetCharacter) {
		this.lienKetCharacter = lienKetCharacter;
	}

	public int getLienKetKing() {
		return lienKetKing;
	}

	public int getLienKetDynasty() {
		return lienKetDynasty;
	}

	public int getLienKetCharacter() {
		return lienKetCharacter;
	}

	public LinkedList<Character> getCharacters() {
		return characters;
	}

	public LinkedList<King> getKings() {
		return kings;
	}

	public LinkedList<Dynasty> getDynastys() {
		return dynastys;
	}

}