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
    }

