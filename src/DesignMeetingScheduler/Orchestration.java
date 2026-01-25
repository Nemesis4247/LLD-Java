package DesignMeetingScheduler;

import DesignMeetingScheduler.booking.Booking;
import DesignMeetingScheduler.booking.Recurrence;
import DesignMeetingScheduler.meeting.MeetingRoom;
import DesignMeetingScheduler.notfications.EmailNotificationService;
import DesignMeetingScheduler.notfications.SMSNotificationService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Orchestration {
    public static void main(String[] args) {
        Scheduler meetingScheduler = new MeetingScheduler();
        meetingScheduler.addMeetingRoom(new MeetingRoom(1, 10));
        meetingScheduler.addMeetingRoom(new MeetingRoom(2, 12));

        LocalDateTime start1 = LocalDateTime.of(2025, 10, 26, 16, 0);
        LocalDateTime end1 = LocalDateTime.of(2025, 10, 26, 16, 30);
        LocalDateTime start2 = LocalDateTime.of(2025, 10, 26, 16, 30);
        LocalDateTime end2 = LocalDateTime.of(2025, 10, 26, 17, 0);

        List<MeetingRoom> meetingRooms = meetingScheduler.availableRooms(start1, end1);
        System.out.println(meetingRooms);

        User user1 = new User(1, "Ishan", "Ishan@xyz.com",
                Arrays.asList(new EmailNotificationService(), new SMSNotificationService()));
        User user2 = new User(2, "Ankur", "Ankur@xyz.com",
                Arrays.asList(new EmailNotificationService(), new SMSNotificationService()));
        Optional<Booking> booking1 = meetingScheduler.createBooking(meetingRooms.get(0), start1, end1, user1,
                "Test Meeting 1", Arrays.asList(user1, user2), Recurrence.ONCE);
        meetingRooms = meetingScheduler.availableRooms(start1, end1);
        System.out.println(meetingRooms);
        Optional<Booking> booking2 = meetingScheduler.createBooking(meetingRooms.get(0), start1, end1, user1,
                "Test Meeting 2", Arrays.asList(user1, user2), Recurrence.ONCE);
        meetingRooms = meetingScheduler.availableRooms(start1, end1);
        System.out.println(meetingRooms);

        meetingRooms = meetingScheduler.availableRooms(start2, end2);
        System.out.println(meetingRooms);
        Optional<Booking> booking3 = meetingScheduler.createBooking(meetingRooms.get(0), start1, end1, user1,
                "Test Meeting 3", Arrays.asList(user1, user2), Recurrence.ONCE);
        booking3 = meetingScheduler.createBooking(meetingRooms.get(0), start2, end2, user1,
                "Test Meeting 3", Arrays.asList(user1, user2), Recurrence.ONCE);

    }
}
