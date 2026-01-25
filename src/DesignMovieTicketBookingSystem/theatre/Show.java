package DesignMovieTicketBookingSystem.theatre;

import DesignMovieTicketBookingSystem.movie.Movie;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class Show {
    Movie movie;
    String hallNo;
    int theatreId;
    List<Seat> seats;
    LocalDate businessDate;
    LocalDateTime startTime;
    Map<SeatCategory, Integer> priceChart;
    Map<Seat, Boolean> seatAvailability;

    LocalDateTime getEndTime() {
        return startTime.plusMinutes(movie.getDurationInMinutes());
    }
}


