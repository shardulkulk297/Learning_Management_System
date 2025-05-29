package com.springboot.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class LmsApplication {

	public static void main(String[] args) {


		SpringApplication.run(LmsApplication.class, args);
	}

}
