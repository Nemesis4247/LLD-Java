package DesignMovieTicketBookingSystem.controllers;

import DesignMovieTicketBookingSystem.movie.Movie;
import DesignMovieTicketBookingSystem.theatre.Show;
import DesignMovieTicketBookingSystem.theatre.Theatre;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class TheatreController {
    Map<String, List<Theatre>> cityToTheatres;
    List<Theatre> allTheatres;

    public TheatreController() {
        cityToTheatres = new HashMap<>();
        allTheatres = new ArrayList<>();
    }

    public List<Theatre> getTheatresForCity(String city) {
        return cityToTheatres.getOrDefault(city, new ArrayList<>());
    }

    public Map<Theatre, List<Show>> getMovieShows(String city, Movie movie, LocalDate date) {
        Map<Theatre, List<Show>> res = new HashMap<>();
        for (Theatre theatre : cityToTheatres.getOrDefault(city, new ArrayList<>())) {
            for (Show show : theatre.getShows()) {
                if (show.getMovie().equals(movie) && show.getBusinessDate().equals(date)) {
                    res.putIfAbsent(theatre, new ArrayList<>());
                    res.get(theatre).add(show);
                }
            }
        }
        return res;
    }
}
