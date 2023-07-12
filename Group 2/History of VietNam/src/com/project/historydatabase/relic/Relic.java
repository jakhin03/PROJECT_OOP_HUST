package com.project.historydatabase.relic;

import java.util.LinkedList;

import com.project.historydatabase.dynasty.Dynasty;
import com.project.historydatabase.figure.Character;
import com.project.historydatabase.figure.King;

public class Relic{
	private String location;
	private String type;
	private String rank;
	private String desc;
	private LinkedList<Character> characters;
	private LinkedList<King> kings;
	private LinkedList<Dynasty> dynasties ;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public LinkedList<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(LinkedList<Character> characters) {
		this.characters = characters;
	}

	public LinkedList<King> getKings() {
		return kings;
	}

	public void setKings(LinkedList<King> kings) {
		this.kings = kings;
	}

	public String getType() {
		return type;
	}

	public String getRank() {
		return rank;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public LinkedList<Dynasty> getDynasties() {
		return dynasties;
	}

	public void setDynasties(LinkedList<Dynasty> dynasties) {
		this.dynasties = dynasties;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Relic(String name, String location, String type, String rank, String desc, LinkedList<Character> characters,
			LinkedList<King> kings, LinkedList<Dynasty> dynasties) {
		super();
		this.name = name;
		this.location = location;
		this.type = type;
		this.rank = rank;
		this.desc = desc;
		this.characters = characters;
		this.kings = kings;
		this.dynasties = dynasties;
	}
}
