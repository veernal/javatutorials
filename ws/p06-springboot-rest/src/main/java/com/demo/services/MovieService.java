package com.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.models.Movie;
import com.demo.utils.DummyDbRepo;

@Service
public class MovieService {
	
	@Autowired
	private DummyDbRepo repo;

	public List<Movie> getAllMovies(){
		System.out.println("in service: get all movies");
		return repo.getMovies();
	}
	
	public void createNewMovie(Movie movie){
		// validate
		repo.addMovie(movie);
	}
	
	
}
