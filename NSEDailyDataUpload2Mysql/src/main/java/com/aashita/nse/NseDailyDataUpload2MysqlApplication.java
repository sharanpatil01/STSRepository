package com.aashita.nse;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NseDailyDataUpload2MysqlApplication {

	public static void main(String[] args) throws IOException {
//		SpringApplication.run(NseDailyDataUpload2MysqlApplication.class, args);
		
		STSFileUtils.main(args);
	}

}
