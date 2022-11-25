package com.demo.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

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

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", release=" + release + ", rating=" + rating + "]";
	}
	public Movie(int id, String title, String director, LocalDate release, double rating) {
		this(title, director, release, rating);
		this.id = id;
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
