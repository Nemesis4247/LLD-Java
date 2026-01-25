package DesignElevatorSystem.elevatorSystem.selectionStrategies;

import DesignElevatorSystem.Direction;
import DesignElevatorSystem.elevatorCar.ElevatorCar;

import java.util.List;
import java.util.Optional;

public interface ElevatorSelectionStrategy {
    public Optional<ElevatorCar> findElevator(Direction direction, int floor, List<ElevatorCar> elevators);
}
