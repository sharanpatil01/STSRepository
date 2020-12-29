package com.aashita.random.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


public class AdhocUtil {
	protected SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	protected Properties props = null;
	
//	 =========== DB HELPERS ===================
	public boolean loadProperties(String filename) {
		boolean retVal = false;
		try {
			props = new Properties();
			InputStream in = new FileInputStream(filename);
			props.load(in);
			in.close();
			in = null;
			retVal = true;
		} catch (FileNotFoundException fnfex) {
			fnfex.printStackTrace(System.out);
		} catch (IOException ioex) {
			ioex.printStackTrace(System.out);
		} catch (RuntimeException rex) {
			rex.printStackTrace(System.out);
		}
		return retVal;
	}
	
	
	public Date validateDate(String date) {
		Date myDate = null;

		try {
			myDate = sdf.parse(date);
		} catch (Exception e) {
			System.out
					.println("\tThe <date> you entered is invalid.\n\tPlease enter a date in the format \"mm/dd/yyyy\".");
			System.exit(-1);
		}
		return myDate;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AdhocUtil util = new AdhocUtil();
		util.loadProperties("C:\\workspaces\\msworkspace4\\RandomGenProj\\src\\main\\resources\\build.properties");
		
		println(util.props.getProperty("JDBC_URL"));
		
	}
	
	static private void println(String msg){
		System.out.println(msg);
	}

}
