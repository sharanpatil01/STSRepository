package com.aashita.random.connectionpool;

public class ConnectionHelper {

	
	private static void println(String level, String msg){
		System.out.println(level+ " : "+msg);
	}
	
	public static void traceLog(String msg) {
		println("trace",msg);
	}

	public static void errorLog(String msg, Exception e) {
		println("error",e,msg);
	}

	private static void println(String level, Exception e, String msg) {
		System.err.println(level+ " : "+msg);		
	}

	public static void infoLog(String msg) {
		println("info",msg);
	}

	public static void errorLog(String msg) {
		println("error",msg);
	}

}
