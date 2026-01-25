package DesignParkingLot.park;

import DesignParkingLot.Floor;
import DesignParkingLot.ParkingSpot;
import DesignParkingLot.VechicleType;

import java.util.List;
import java.util.Optional;

public interface ParkingStrategy {
    public Optional<ParkingSpot> getParkingSpot(VechicleType vechicleType, List<Floor> parkingFloors);
}
