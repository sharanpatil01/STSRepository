package com.aashita.ws.contacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonsSoapWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonsSoapWsApplication.class, args);
		NSEStocksPublisher.main(null);
	}

}
