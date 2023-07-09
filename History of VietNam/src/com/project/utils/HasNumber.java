package com.project.utils;

public class HasNumber {
	public static String hasNumber(String data) {
		char[] chars = data.toCharArray();
		for (char charac : chars) {
			if (Character.isDigit(charac))
				return data;
		}
		return "";
	}
}
