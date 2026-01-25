package DesignParkingLot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PandeysParkingLot implements ParkingLot{
    private List<Floor> parkingFloorList;
    ParkingManager parkingManager;

    public PandeysParkingLot(ParkingManager parkingManager) {
        this.parkingManager = parkingManager;
        parkingFloorList = new ArrayList<>();
    }
    
    @Override
    public Ticket enter(Vechicle vechicle) {
        Optional<ParkingSpot> parkingSpot = parkingManager.getParkingStrategy().getParkingSpot(
                vechicle.getVechicleType(), this.parkingFloorList);
        return parkingSpot.map(spot -> {
            spot.setVacant(false);
            return new Ticket(vechicle, spot, LocalDateTime.now());
        }).orElse(null);
    }

    @Override
    public Invoice exit(Ticket ticket) {
        LocalDateTime exitTS = LocalDateTime.now();
        return new Invoice(ticket, exitTS, parkingManager.getCostingStrategy().getCost(ticket, exitTS));
    }

    @Override
    public boolean removeParkingFloor(Floor parkingFloor) {
        return parkingFloorList.remove(parkingFloor);
    }

    @Override
    public boolean addParkingFloor(Floor parkingFloor) {
        return parkingFloorList.add(parkingFloor);
    }
}
