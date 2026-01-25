package DesignParkingLot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Ticket {
    private Vechicle vechicle;
    private ParkingSpot parkingSpot;
    private LocalDateTime entryTime;
}
