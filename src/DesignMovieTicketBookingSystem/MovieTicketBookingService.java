package DesignMovieTicketBookingSystem;

import DesignMovieTicketBookingSystem.controllers.MovieController;
import DesignMovieTicketBookingSystem.controllers.TheatreController;
import DesignMovieTicketBookingSystem.movie.Movie;
import DesignMovieTicketBookingSystem.theatre.Seat;
import DesignMovieTicketBookingSystem.theatre.Show;
import DesignMovieTicketBookingSystem.theatre.Theatre;
import DesignMovieTicketBookingSystem.ticket.Ticket;
import DesignMovieTicketBookingSystem.ticket.TicketStatus;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MovieTicketBookingService implements BookingService {
    TheatreController theatreController;
    MovieController movieController;


    @Override
    public List<Movie> getMovies(String city) {
        return movieController.getMoviesForCity(city);
    }

    @Override
    public List<Theatre> getTheatres(String city) {
        return theatreController.getTheatresForCity(city);
    }

    @Override
    public List<Show> getTheatreShows(Theatre theatre, LocalDate date) {
        return theatre.getShows().stream().filter(
                show -> show.getBusinessDate().equals(date)).collect(Collectors.toList());
    }

    @Override
    public Map<Theatre, List<Show>> getMovieShows(String city, Movie movie, LocalDate date) {
        return theatreController.getMovieShows(city, movie, date);
    }

    @Override
    public List<Seat> getShowSeats(Show show) {
        return show.getSeats();
    }

    @Override
    public Optional<Ticket> bookSeats(List<Seat> seats, Show show, Theatre theatre) {
        synchronized (show) {
            boolean isBooked = show.getSeatAvailability().entrySet().stream()
                    .filter(e -> seats.contains(e.getKey()))
                    .anyMatch(e -> !e.getValue());
            if (isBooked) {
                // seats are booked already
                return Optional.empty();
            }
            seats.forEach(seat -> show.getSeatAvailability().put(seat, false));
            return Optional.of(new Ticket(seats, show, theatre, TicketStatus.CONFIRMED));
        }
    }
}
