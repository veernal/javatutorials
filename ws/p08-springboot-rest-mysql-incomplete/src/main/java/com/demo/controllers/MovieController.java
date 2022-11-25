package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entities.Movie;
import com.demo.services.MovieService;

@RestController // = @Controller + @ResponseBody
@RequestMapping("/api/movies")
public class MovieController {

	@Autowired
	private MovieService service;
	
	@GetMapping()
	public List<Movie> getAllMovies(){
		return service.getAllMovies();
	}
	
	@PostMapping()
	public Movie createNewMovie(@RequestBody Movie movie){
		return service.saveMovie(movie);
	}

	@GetMapping("/{id}")
	public Movie getMovieByPk(@PathVariable int id) throws Exception{
		return service.getMovieById(id);
	}

	@DeleteMapping("/{id}")
	public Movie removeMovieByPk(@PathVariable int id) throws Exception{
		return service.deleteMovie(id);
	}

	@GetMapping("rating") // /api/movies?value=4.8
	public List<Movie> removeMovieByPk(@RequestParam double value) throws Exception{
		return service.getMoviesGreaterThanRating(value);
	}


	/*
	 * Create movie - done
	 * Find all - done
	 * findMovieById - treat index as id
	 * deleteMovie
	 * filterByRating
	 * 
	 * findMoviesByTitle
	 * updateMovie
	 */
	
}
