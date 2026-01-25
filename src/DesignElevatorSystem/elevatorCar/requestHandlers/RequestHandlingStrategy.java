package DesignElevatorSystem.elevatorCar.requestHandlers;

import DesignElevatorSystem.Direction;
import DesignElevatorSystem.elevatorCar.ElevatorCar;

public interface RequestHandlingStrategy {
    public boolean handle(Direction direction, int floor);
    public int getNextStop(ElevatorCar elevatorCar);
}
