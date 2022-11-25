package com.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.Movie;
import com.demo.exceptions.MovieException;
import com.demo.repositories.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repo;

	public List<Movie> getAllMovies(){
		return repo.findAll();
	}

	public Movie getMovieById(int id) throws MovieException{

		Optional<Movie> optional =repo.findById(id);

		if(optional.isPresent()){
			return optional.get();
		} else {
			throw new MovieException("Movie with id "+id+" not found in db");
		}
	}
	
	public Movie saveMovie(Movie m){
		// validation
		return repo.save(m);
	}

	public Movie deleteMovie(int id) throws MovieException{

		try{
			Movie m  =  getMovieById(id);

			if(m!=null){
				repo.deleteById(id);
			}
			
			return m;
		} catch(Exception e){
			throw new MovieException("Movie with id "+id+" not found in db, can not delete", e);
		}

	}

	public Movie updateMovie(int id, Movie m){
		return null;
	}

	public Movie getMovieByTitle(String title){
		return null;
	}
	
	public List<Movie> getMoviesGreaterThanRating(double rating){
		return repo.getMoviesGreaterThanRating(rating);
	}
	

}
