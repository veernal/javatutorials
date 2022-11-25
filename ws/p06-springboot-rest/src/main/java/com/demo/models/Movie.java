package com.demo.models;

import java.time.LocalDate;

public class Movie {

	private String title;
	private String director;
	private LocalDate release;
	private double rating;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public LocalDate getRelease() {
		return release;
	}
	public void setRelease(LocalDate release) {
		this.release = release;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Movie [title=" + title + ", director=" + director + ", release=" + release + ", rating=" + rating + "]";
	}
	public Movie(String title, String director, LocalDate release, double rating) {
		super();
		this.title = title;
		this.director = director;
		this.release = release;
		this.rating = rating;
	}
	public Movie() {
	}
	
}
