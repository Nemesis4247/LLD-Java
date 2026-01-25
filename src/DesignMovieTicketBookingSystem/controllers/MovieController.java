package DesignMovieTicketBookingSystem.controllers;

import DesignMovieTicketBookingSystem.movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class MovieController {
    Map<String, List<Movie>> cityToMovies;
    List<Movie> allMovies;

    public MovieController() {
        cityToMovies = new HashMap<>();
        allMovies = new ArrayList<>();
    }

    public List<Movie> getMoviesForCity(String city) {
        return cityToMovies.getOrDefault(city, new ArrayList<>());
    }
}
