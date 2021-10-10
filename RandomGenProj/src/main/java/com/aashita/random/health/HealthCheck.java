package com.aashita.random.health;

import java.time.Duration;
import java.time.Instant;

public class HealthCheck extends Thread {

	
	public Instant appStartDt = Instant.now();
	
	public void run() {
		try {
			Thread.sleep(5000);
			
			System.out.println("2. RandomGen-App is up and running from : " + appDuration());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

	public  String appDuration() {
		Duration duration =  Duration.between(appStartDt, Instant.now()) ;
		
		return 	duration.toMillis() + " milli seconds.";
		
	}
}
