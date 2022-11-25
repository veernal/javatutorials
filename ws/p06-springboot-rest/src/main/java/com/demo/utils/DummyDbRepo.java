package com.demo.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.models.Movie;

@Repository
public class DummyDbRepo {
	
	private List<Movie> movies = new ArrayList<Movie>();
	
	public void addMovie(Movie m) {
		movies.add(m);
	}
	
	public List<Movie> getMovies(){
		System.out.println("in repo: get all movies");
		
		return movies;
	}

	
//	public static void main(String[] args) {
//		List<Movie> movies = new ArrayList<Movie>();
//		movies.add(new Movie());
//		System.out.println(new ArrayList(movies));
//	}
}
