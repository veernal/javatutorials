package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.models.Movie;
import com.demo.services.MovieService;

@RestController // = @Controller + @ResponseBody
@RequestMapping("/api/movies")
public class MovieController {

	@Autowired
	private MovieService service;
	
	@GetMapping()
//	@ResponseBody
	public List<Movie> getAllMovies(){
		System.out.println("in controller: get all movies");
		return service.getAllMovies();
	}
	
	@PostMapping()
	public void createNewMovie(@RequestBody Movie movie){
		service.createNewMovie(movie);
		// delete all movies
	}



	/*
	 * Create movie - done
	 * Find all - done
	 * 
	 * findMovieByTitle
	 * findMovieById - treat index as id
	 * deleteMovie
	 * updateMovie
	 */
	
}
