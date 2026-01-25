package DesignParkingLot;

public interface ParkingLot {
    public Ticket enter(Vechicle vechicle);

    public Invoice exit(Ticket ticket);

    public boolean addParkingFloor(Floor parkingFloor);

    public boolean removeParkingFloor(Floor parkingFloor);
}
