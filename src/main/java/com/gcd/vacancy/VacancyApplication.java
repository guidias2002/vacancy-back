package com.gcd.vacancy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class VacancyApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacancyApplication.class, args);
	}

}
