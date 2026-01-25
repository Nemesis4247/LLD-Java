package DesignParkingLot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingSpot {
    private int floorNo;
    private boolean vacant;
    private VechicleType vechicleType;
    private int distanceFromExit;
}
