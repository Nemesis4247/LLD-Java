package DesignMeetingScheduler.booking;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookingHistory {
    List<Booking> historicalBookings;

    public BookingHistory() {
        historicalBookings = new ArrayList<>();
    }
}
