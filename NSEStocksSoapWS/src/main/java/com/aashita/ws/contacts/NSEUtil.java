package com.aashita.ws.contacts;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class NSEUtil {
	private static Random random = new Random();

	
	public static Ohlc5 getOhlcRec(String symbol) {
		Ohlc5 ohlc = new Ohlc5();
		
		ohlc.setId(Math.abs(random.nextInt()%1000));
		ohlc.setSymbol(symbol);
		
		float moneyamt = 100.10f;
		moneyamt = 	random.nextFloat() * 1000;

		/*
			Locale locale = new Locale("en", "US");      
			NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
			System.out.println(moneyamt + "  after formatting : "+ currencyFormatter.format(moneyamt));
		*/
		
		ohlc.setLast_price(moneyamt);
		return ohlc;
	}
	
	public static List<Ohlc5> getOhlc(String stockname) {
		ArrayList<Ohlc5> list = new ArrayList<Ohlc5>();
		list.add(getOhlcRec(stockname));
		list.add(getOhlcRec(stockname));
		return list;
	}

	public static List<Ohlc5> getOhlcListForStock(String stockname) {
		ArrayList<Ohlc5> list = new ArrayList<Ohlc5>();
		list.add(getOhlcRec(stockname));
		list.add(getOhlcRec(stockname));
		return list;
		}

	public static Ohlc5 getOhlcPrice(String stockname) {
		Ohlc5 ohlc = getOhlcRec(stockname);
		return ohlc;
	}

	public static List<Ohlc5> getAllOhlc() {
		ArrayList<Ohlc5> list = new ArrayList<Ohlc5>();
		list.add(getOhlcRec("EXIDEIND"));
		list.add(getOhlcRec("HIKAL"));
		list.add(getOhlcRec("CONCUR"));
		list.add(getOhlcRec("DABUR"));
		list.add(getOhlcRec("ITC"));
		list.add(getOhlcRec("NIFTYBEES"));
		return list;
	
	}
	
	
	public static List<Ohlc5> getNonNseStocksList() {
		
		ArrayList<Ohlc5> list = new ArrayList<Ohlc5>();
		list.add(getOhlcRec("IBM"));
		list.add(getOhlcRec("BMO"));
		list.add(getOhlcRec("BOA"));
		list.add(getOhlcRec("WESTPAC"));
		list.add(getOhlcRec("CBA"));
		list.add(getOhlcRec("S&P500"));
		return list;
		
	}

public static List<String> getNonNseStocksNameList() {
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("IBM");
		list.add("BMO");
		list.add("BOA");
		list.add("WESTPAC");
		list.add("CBA");
		list.add("S&P500");
		return list;
		
	}
}
