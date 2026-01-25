package DesignParkingLot.park;

import DesignParkingLot.Floor;
import DesignParkingLot.ParkingSpot;
import DesignParkingLot.VechicleType;

import java.util.*;

public class SimpleParkingStrategy implements ParkingStrategy {

    private static Map<VechicleType, List<VechicleType>> acceptedParkingTypes = Map.of(
            VechicleType.BIKE, Arrays.asList(VechicleType.BIKE, VechicleType.CAR),
            VechicleType.CAR, List.of(VechicleType.CAR),
            VechicleType.EV, List.of(VechicleType.EV)
    );

    @Override
    public Optional<ParkingSpot> getParkingSpot(VechicleType vechicleType, List<Floor> parkingFloors) {
        return parkingFloors.stream().map(Floor::getParkingSpots)
                .flatMap(Collection::stream).filter(
                        spot -> spot.isVacant()
                                && acceptedParkingTypes.get(vechicleType).contains(spot.getVechicleType())).findFirst();
    }
}
