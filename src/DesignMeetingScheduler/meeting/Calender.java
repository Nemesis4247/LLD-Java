package DesignMeetingScheduler.meeting;

import DesignMeetingScheduler.booking.Booking;
import DesignMeetingScheduler.booking.BookingStatus;
import DesignMeetingScheduler.booking.Recurrence;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Data
public class Calender {
    int roomId;
    List<Booking> calender;

    public Calender(int roomId) {
        calender = new ArrayList<>();
        this.roomId = roomId;
    }

    public boolean addMeeting(Booking booking) {
        return calender.add(booking);
    }


    public boolean isSlotAvailable(LocalDateTime start, LocalDateTime end) {
        boolean isConflict = false;
        for (Booking booking : getCalender()) {
            if (booking.getStatus().equals(BookingStatus.ACTIVE) && Recurrence.ONCE.equals(booking.getRecurrence())) {
                isConflict = isConflict || !(booking.getEndTime().isBefore(start) || booking.getEndTime().isEqual(start)
                        || booking.getStartTime().isAfter(end) || booking.getStartTime().isEqual(end));
            }
        }
        return !isConflict;
    }
}
