package com.citiustech.MediGenie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MediGenieApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediGenieApplication.class, args);
	}

}
