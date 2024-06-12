package com.mattsbarbosa.boulderScoreAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class BoulderScoreApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoulderScoreApiApplication.class, args);
	}

}
