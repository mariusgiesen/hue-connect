package com.mgdo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HueConnectApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HueConnectApiApplication.class, args);
	}

}
