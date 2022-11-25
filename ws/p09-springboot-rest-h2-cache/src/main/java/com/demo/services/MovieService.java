package com.demo.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.demo.entities.Movie;
import com.demo.repositories.MovieRepository;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repo;

	public List<Movie> getAllMovies(){
		return repo.findAll();
	}

	@Cacheable(value = "moviearea", key="#id")
	public Movie getMovieById(int id) throws Exception{

		System.out.println("("+LocalDateTime.now()+") - Finding movie by id: "+id);

		Optional<Movie> optional =repo.findById(id);

		if(optional.isPresent()){
			return optional.get();
		} else {
			throw new Exception(id+" not found in db");
		}
	}
	
	public Movie saveMovie(Movie m){
		// validation
		return repo.save(m);
	}

	@CacheEvict(value = "moviearea", key="#id")
	public Movie deleteMovie(int id) throws Exception{

		try{
			Movie m  =  getMovieById(id);

			if(m!=null){
				repo.deleteById(id);
			}
			
			return m;
		} catch(Exception e){
			throw e;
		}

	}

	@CachePut(value = "moviearea", key="#id")
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
