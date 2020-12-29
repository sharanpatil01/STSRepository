package com.aashita.ms.contacts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
//@EnableSwagger2
@EnableSwagger2WebMvc
public class ContactsMsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactsMsApiApplication.class, args);
	}

}
