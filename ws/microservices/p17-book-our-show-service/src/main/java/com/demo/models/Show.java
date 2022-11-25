package com.demo.models;

public class Show {

    private int id;
    private String title;
    private String director;
    private double rating;

    public Show() {}
    public Show(String title, String director, double rating) {
        this.title = title;
        this.director = director;
        this.rating = rating;
    }
    public Show(int id, String title, String director, double rating) {
        this(title, director, rating);
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    @Override
    public String toString() {
        return "Show [id=" + id + ", title=" + title + ", director=" + director + ", rating=" + rating + "]";
    }

}
