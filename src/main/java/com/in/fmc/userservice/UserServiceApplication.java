package com.in.fmc.userservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceApplication.class);


	public static void main(String[] args) {
		
		log.info("################### USER-SERVICE STARTED ###################");
		
		SpringApplication.run(UserServiceApplication.class, args);
		
	}

}
