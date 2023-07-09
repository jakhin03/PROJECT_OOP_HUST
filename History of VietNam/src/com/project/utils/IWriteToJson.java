package com.project.utils;

import java.io.IOException;

import com.google.gson.JsonIOException;

public interface IWriteToJson {
	public void writeToJson() throws JsonIOException, IOException;
	
}
