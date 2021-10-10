package com.aashita.random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aashita.random.health.HealthCheck;

@SpringBootApplication
public class RandomGenProjApplication {

	
	public static void main(String[] args) {
		HealthCheck th = new HealthCheck();
		SpringApplication.run(RandomGenProjApplication.class, args);
		System.out.println("1. RandomGen-app is started...  : " + th.appStartDt );
		
		th.start();
	}

}
