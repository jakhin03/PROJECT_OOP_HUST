package com.project.historydatabase;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class HistoryDatabase {
    public static <T> ObservableList<T> getHistoryDatabase(String dataFile, Class<T> objectType) {
    	String dataPath = "History Data\\";
        Gson gson = new Gson();
		FileReader fileReader;
	
		try {
			fileReader = new FileReader(dataPath + dataFile);
			Type listType = TypeToken.getParameterized(List.class, objectType).getType();
			List<T> objects = gson.fromJson(fileReader, listType);

			return FXCollections.observableList(objects);
		} catch (FileNotFoundException e) {
			System.out.println("Cant find data in path: " + dataPath+dataFile);
			return null;
		}

		
	    }

//    public void addDynasty(Dynasty dynasty) {
//    }
}
//    private List<Dynasty> dynasties;
//    private List<Figure> figures;
//    private List<Festival> festivals;
//    private List<King> kings;
//    private List<Character> characters;
//    private List<Event> events;
//    private List<Relic> relics;
//
//    public HistoryDatabase() {
//        this.dynasties = new ArrayList<>();
//        this.figures = new ArrayList<>();
//        this.festivals = new ArrayList<>();
//        this.kings = new ArrayList<>();
//        this.characters = new ArrayList<>();
//        this.events = new ArrayList<>();
//        this.relics = new ArrayList<>();   
//    }
    
//    public void addDynasty(Dynasty dynasty) {
//        dynasties.add(dynasty);
//    }
//
//    public void addFigure(Figure Figure) {
//        figures.add(Figure);
//    }
//
//    public void addFestival(Festival festival) {
//        festivals.add(festival);
//    }
//    
//    public void addKing(King king) {
//    	kings.add(king);
//    }
//    
//    public void addCharacter(Character character) {
//    	characters.add(character);
//    }
//    
//    public void addEvent(Event event) {
//    	events.add(event);
//    }
//    
//    public void addRelic(Relic relic) {
//    	relics.add(relic);
//    }
//    
//    public List<Dynasty> getDynasties() {
//        return dynasties;
//    }
//
//    public void setDynasties(List<Dynasty> dynasties) {
//        this.dynasties = dynasties;
//    }
//
//    public List<Figure> getFigures() {
//        return figures;
//    }
//
//    public void setFigures(List<Figure> Figures) {
//        this.figures = Figures;
//    }
//
//    public List<Festival> getFestivals() {
//        return festivals;
//    }
//
//    public void setFestivals(List<Festival> festivals) {
//        this.festivals = festivals;
//    }
//
//
//	public List<King> getKings() {
//		return kings;
//	}
//
//
//	public void setKings(List<King> kings) {
//		this.kings = kings;
//	}
//
//
//	public List<Character> getCharacters() {
//		return characters;
//	}
//
//
//	public void setCharacters(List<Character> characters) {
//		this.characters = characters;
//	}
//
//
//	public List<Event> getEvents() {
//		return events;
//	}
//
//
//	public void setEvents(List<Event> events) {
//		this.events = events;
//	}
//
//
//	public List<Relic> getRelics() {
//		return relics;
//	}
//
//
//	public void setRelics(List<Relic> relics) {
//		this.relics = relics;
//	}
//    
//    

