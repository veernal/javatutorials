package com.demo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.entities.Movie;
import com.demo.services.MovieService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private MovieService service;

	@Override
	public void run(String... args) throws Exception {
		// List<Movie> movies = Arrays.asList(
		// 	new Movie("Spiderman is kid", "Mark", LocalDate.of(2020, Month.FEBRUARY, 15), 4.8),
		// 	new Movie("Ironman is rich", "Miley", LocalDate.of(2020, Month.JANUARY, 31), 4.9),
		// 	new Movie("Hulk is not green", "Clark", LocalDate.of(2019, Month.OCTOBER, 5), 4.7)
		// );

		// movies.forEach(movie->{
		// 	service.saveMovie(movie);
		// });
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
