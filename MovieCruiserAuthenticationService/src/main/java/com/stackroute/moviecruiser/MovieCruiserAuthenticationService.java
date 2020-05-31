package com.stackroute.moviecruiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
public class MovieCruiserAuthenticationService {

	public MovieCruiserAuthenticationService() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		SpringApplication.run(MovieCruiserAuthenticationService.class, args);
	}
}
