package com.solvd.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TwitterInitializer {

	public static void main(final String[] args) {
		SpringApplication.run(TwitterInitializer.class, args);
	}

}
