package com.demo;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.models.Movie;
import com.demo.services.MovieService;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {

	@Autowired
	private MovieService service;

	@Override
	public void run(String... args) throws Exception {
		service.createNewMovie(new Movie("Superman is swimming", "Miley", LocalDate.of(2019, Month.DECEMBER, 5), 4.7));
	}

	public static void main(String[] args) {
		SpringApplication.run(MyApplication.class, args);
	}

}
