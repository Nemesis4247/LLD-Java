package DesignElevatorSystem.elevatorSystem;

import DesignElevatorSystem.Direction;
import DesignElevatorSystem.elevatorCar.ElevatorCar;

public interface ElevatorSystem {
    public ElevatorCar requestElevator(Direction direction, int floor);
    public boolean requestFloor(ElevatorCar car, int floor);
    public void step();
}
