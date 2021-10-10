package com.aashita.random.health;

import java.time.Duration;
import java.time.Instant;

public class HealthCheck extends Thread {

	private static long callctr = 0;
	public static Instant appStartDt = Instant.now();
	
	public void run() {
		try {
			Thread.sleep(5000);
			
			System.out.println(callctr++ +". RandomGen-App is up and running from : " + appDuration());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

	public static  String appDuration() {
		Duration duration =  Duration.between(appStartDt, Instant.now()) ;
		String dur = 	duration.toMillis()/1000 + " milli seconds.";
		System.out.println(callctr++ +". RandomGen-App is up and running from : " + dur );
		
		return dur;
		
	}
}
