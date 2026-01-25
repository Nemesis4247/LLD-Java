package DesignMeetingScheduler;

import DesignMeetingScheduler.booking.Booking;
import DesignMeetingScheduler.booking.BookingHistory;
import DesignMeetingScheduler.booking.BookingStatus;
import DesignMeetingScheduler.booking.Recurrence;
import DesignMeetingScheduler.meeting.MeetingRoom;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class MeetingScheduler implements Scheduler {
    List<MeetingRoom> meetingRooms;
    BookingHistory history;

    public MeetingScheduler() {
        meetingRooms = new ArrayList<>();
        history = new BookingHistory();
    }

    @Override
    public boolean addMeetingRoom(MeetingRoom room) {
        return this.meetingRooms.add(room);
    }

    @Override
    public boolean isRoomAvailable(MeetingRoom room,
                                   LocalDateTime startTime, LocalDateTime endTime) {
        return room.getCalender().isSlotAvailable(startTime, endTime);
    }

    @Override
    public Optional<Booking> createBooking(MeetingRoom room, LocalDateTime startTime,
                                           LocalDateTime endTime, User organizer, String subject,
                                           List<User> participants, Recurrence recurrence) {
        if (!isRoomAvailable(room, startTime, endTime)) return Optional.empty();
        Booking booking = new Booking(room, subject, organizer,
                participants, startTime, endTime, recurrence, BookingStatus.ACTIVE);
        room.getCalender().addMeeting(booking);
        participants.forEach(participant -> participant.update(booking));
        this.saveToHistory(booking);
        return Optional.of(booking);
    }

    public boolean saveToHistory(Booking booking) {
        return history.getHistoricalBookings().add(booking);
    }

    @Override
    public boolean cancelBooking(Booking booking, User user) {
        if (booking.getOrganizer().equals(user)) {
            booking.setStatus(BookingStatus.CANCELLED);
            booking.getParticipants().forEach(participant -> participant.update(booking));
            return true;
        } else {
            // Unauthorized
            return false;
        }
    }

    @Override
    public List<MeetingRoom> availableRooms(LocalDateTime start, LocalDateTime end) {
        return this.meetingRooms.stream().filter(
                room -> isRoomAvailable(room, start, end)).collect(Collectors.toList());
    }
}
