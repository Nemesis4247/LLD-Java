package DesignMeetingScheduler;

import DesignMeetingScheduler.booking.Booking;
import DesignMeetingScheduler.booking.Recurrence;
import DesignMeetingScheduler.meeting.MeetingRoom;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface Scheduler {

    public boolean addMeetingRoom(MeetingRoom room);

    boolean isRoomAvailable(MeetingRoom room,
                            LocalDateTime startTime, LocalDateTime endTime);

    Optional<Booking> createBooking(MeetingRoom room, LocalDateTime startTime,
                                    LocalDateTime endTime, User organizer, String subject,
                                    List<User> participants, Recurrence recurrence);

    boolean cancelBooking(Booking booking, User user);

    List<MeetingRoom> availableRooms(LocalDateTime start, LocalDateTime end);
}
