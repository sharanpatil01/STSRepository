package com.aashita.random.controller;

import java.util.Random;

public class RandomUtil {

	final private String[] fnames = { "Anil", "Sunil", "Mahesh", "Jagdish",
			"Vijay", "Basav", "Aakash", "Pradeep", "Arvind", "Prem", "Gourav" };

	final private String[] lnames = { "Patil", "Gowda", "Biradar", "Bhalke",
			"Pasarge", "Swamy", "Ranjolekar", "Sutrave" };

	final private String[] cities = { "Bidar", "Bangalore", "Delhi", "Chennai",
			"Kolkatta", "Mumbai", "Hyderabad", "Pune" };

	final private String[] states = { "AP", "Kar", "Kerala", "TN", "MH", "MP",
			"Guj", "UP" };

	private Random random = new Random();

	public String getRandomFName() {
		return fnames[random.nextInt(fnames.length)];
	}

	public String getRandomLName() {
		return lnames[random.nextInt(lnames.length)];
	}

	public String getRandomCity() {
		return cities[random.nextInt(cities.length)];
	}

	public String getRandomState() {
		return states[random.nextInt(states.length)];
	}

	public String getRandomZipCode() {
		return getNDigitNo(random.nextInt(99999), 5);
	}

	public String getRandomId() {
		return getNDigitNo(random.nextInt(99999), 5);
	}

	public String getNextNDigitNo(int digits) {
		return getNDigitNo(random.nextInt(9999999), digits);
	}

	public String getNDigitNo(int number, int digits) {
		String num = number + "";
		String zeros = "";

		if (num.length() > digits) {
			num = num.substring(0, digits);
		}
		if (num.length() < digits) {
			int diff = digits - num.length();
			for (int i = 0; i < diff; i++) {
				zeros += "0";
			}

			num = zeros + num;
		}
		// println("Random Num : "+ num);
		return num;
	}

	public void println(String msg) {
		System.out.println(msg);
	}

	public static void main(String[] args) {
		RandomUtil ru = new RandomUtil();
		ru.println( "Random no  = "+  ru.getNextNDigitNo(9)  );
		
	}
}
