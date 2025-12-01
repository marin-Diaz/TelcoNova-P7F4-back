package com.telconova.supportsuite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.telconova.supportsuite")
public class SuportsuiteApplication {
	public static void main(String[] args) {
		SpringApplication.run(SuportsuiteApplication.class, args);
	}
}