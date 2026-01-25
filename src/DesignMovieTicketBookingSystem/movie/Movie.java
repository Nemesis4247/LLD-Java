package DesignMovieTicketBookingSystem.movie;

import lombok.Data;

@Data
public class Movie {
    int movieId;
    String name;
    int durationInMinutes;
}
