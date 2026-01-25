package DesignElevatorSystem.elevatorSystem;

import DesignElevatorSystem.Direction;
import DesignElevatorSystem.elevatorCar.ElevatorCar;
import DesignElevatorSystem.elevatorSystem.selectionStrategies.ElevatorSelectionStrategy;

import java.util.List;
import java.util.Optional;

public class SimpleElevatorSystem implements ElevatorSystem{
    List<ElevatorCar> elevators;
    ElevatorSelectionStrategy selectionStrategy;

    public SimpleElevatorSystem() {

    }
    @Override
    public ElevatorCar requestElevator(Direction direction, int floor) {
        if (Direction.IDLE.equals(direction)) {
            throw new RuntimeException("Invalid direction");
        }
        Optional<ElevatorCar> optionalElevatorCar = selectionStrategy.findElevator(direction, floor, elevators);
        if (optionalElevatorCar.isEmpty()) {
            throw new RuntimeException("Elevators not found");
        }
        ElevatorCar elevatorCar = optionalElevatorCar.get();
        elevatorCar.getRequestHandler().handle(direction, floor);
        return elevatorCar;
    }

    @Override
    public boolean requestFloor(ElevatorCar car, int floor) {
//        Direction direction = floor < car.getFloor() ?
        return car.getRequestHandler().handle(car.getDirection(), floor);
    }

    @Override
    public void step() {
        this.elevators.forEach(ElevatorCar::move);
    }
}
