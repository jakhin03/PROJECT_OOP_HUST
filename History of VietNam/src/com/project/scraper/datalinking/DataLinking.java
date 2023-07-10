package com.project.scraper.datalinking;

import java.util.List;

import com.project.historydatabase.figure.Character;
import com.project.historydatabase.figure.King;

public abstract class DataLinking{
	protected List<Character> listObservablesCharacter;
	protected List<King> listObservablesKing;
	
	public abstract void genLink(String tenNguoiTho);

}