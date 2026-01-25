package DesignMovieTicketBookingSystem.ticket;

import DesignMovieTicketBookingSystem.theatre.Seat;
import DesignMovieTicketBookingSystem.theatre.Show;
import DesignMovieTicketBookingSystem.theatre.Theatre;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Ticket {
    List<Seat> seats;
    Show show;
    Theatre theatre;
    TicketStatus ticketStatus;
}
