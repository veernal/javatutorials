package com.demo.services;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Movie getMovieById(int id) throws Exception{

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

	public Movie updateMovie(int id) throws Exception {
		try{
				repo.findById(id).ifPresent(movie -> {
				movie.setRelease(LocalDate.of(2022, Month.APRIL, 13));
				repo.save(movie);
			});
		}catch (Exception e){
			throw new Exception(id+ " not available");
		}
		return repo.getById(id);
	}

	public Movie getMovieByTitle(String title){
		return null;
	}
	
	public List<Movie> getMoviesGreaterThanRating(double rating){

		return repo.getMoviesGreaterThanRating(rating);
	}

}
