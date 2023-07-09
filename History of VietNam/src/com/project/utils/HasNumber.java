package com.project.utils;

public class HasNumber {
	public static String hasNumber(String data) {
		char[] chars = data.toCharArray();
		for (char c : chars) {
			if (Character.isDigit(c))
				return data;
			}	
		return "";
	}
}

