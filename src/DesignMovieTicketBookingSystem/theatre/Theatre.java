package DesignMovieTicketBookingSystem.theatre;

import lombok.Data;

import java.util.List;

@Data
public class Theatre {
    List<Show> shows;
    int theatreId;
    String city;
}
