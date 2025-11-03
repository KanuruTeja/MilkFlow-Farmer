package com.example.farmer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//@ImportResource("classpath:applicationContext.xml")
@SpringBootApplication
public class FarmerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmerServiceApplication.class, args);
	}

}
