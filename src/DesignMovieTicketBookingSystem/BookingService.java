package DesignMovieTicketBookingSystem;

import DesignMovieTicketBookingSystem.movie.Movie;
import DesignMovieTicketBookingSystem.theatre.Seat;
import DesignMovieTicketBookingSystem.theatre.Show;
import DesignMovieTicketBookingSystem.theatre.Theatre;
import DesignMovieTicketBookingSystem.ticket.Ticket;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookingService {
    public List<Movie> getMovies(String city);

    public List<Theatre> getTheatres(String city);

    public List<Show> getTheatreShows(Theatre theatre, LocalDate date);

    public Map<Theatre, List<Show>> getMovieShows(String city, Movie movie, LocalDate date);

    public List<Seat> getShowSeats(Show show);

    public Optional<Ticket> bookSeats(List<Seat> seats, Show show, Theatre theatre);
}
