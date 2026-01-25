package DesignMeetingScheduler.meeting;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MeetingRoom {
    int roomId;
    int seatingCapacity;
    Calender calender;

    public MeetingRoom(int roomId, int seatingCapacity) {
        this.roomId = roomId;
        this.seatingCapacity = seatingCapacity;
        calender = new Calender(roomId);
    }
}
