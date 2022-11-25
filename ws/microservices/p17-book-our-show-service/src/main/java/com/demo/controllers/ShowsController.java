package com.demo.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.clients.HerosMoviesClient;
import com.demo.models.Show;

@RestController
@RequestMapping("/shows")
public class ShowsController {

    @Autowired
    private HerosMoviesClient client;
    
    @GetMapping("/dummy")
    public List<Show> getShows(){
        return Arrays.asList(
            new Show(1001, "Shaktiman", "Mukesh", 4.7),
            new Show(1002, "Hanuman", "Arun", 4.9),
            new Show(1003, "Spiderman", "Mike", 4.8),
            new Show(1004, "Superman", "Clark", 4.5)            
        );
    }

    @GetMapping("/movies")
    public List<Show> getShowsFromMoviesService(){
        return client.getMovies();
    }

}
