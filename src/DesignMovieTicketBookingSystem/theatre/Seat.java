package DesignMovieTicketBookingSystem.theatre;

import lombok.Data;

import java.util.Map;

@Data
public class Seat {
    int seatNo;
    char row;
    String hallNo;
    SeatCategory category;
}
