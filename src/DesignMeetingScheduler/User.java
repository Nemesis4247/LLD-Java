package DesignMeetingScheduler;

import DesignMeetingScheduler.booking.Booking;
import DesignMeetingScheduler.booking.BookingStatus;
import DesignMeetingScheduler.notfications.NotificationService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    int userId;
    String name;
    String email;
    List<NotificationService> notificationChannels;

    public void update(Booking booking) {
        String createdOrCancelled = booking.getStatus().equals(BookingStatus.CANCELLED) ? "cancelled" : "scheduled";
        String message = "Meeting has been " + createdOrCancelled + " by " + booking.getOrganizer().getName() +
                ". Subject: " + booking.getSubject() + ". Time: " + booking.getStartTime() + " to " + booking.getEndTime();
        for (NotificationService channel : notificationChannels) {
            channel.sendNotification(this, message);
        }
    }
}
