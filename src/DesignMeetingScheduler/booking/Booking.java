package DesignMeetingScheduler.booking;

import DesignMeetingScheduler.meeting.MeetingRoom;
import DesignMeetingScheduler.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Booking {
    MeetingRoom meetingRoom;
    String subject;
    User organizer;
    List<User> participants;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Recurrence recurrence;
    BookingStatus status;

}
