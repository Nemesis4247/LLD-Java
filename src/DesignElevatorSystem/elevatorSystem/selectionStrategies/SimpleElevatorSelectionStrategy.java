package DesignElevatorSystem.elevatorSystem.selectionStrategies;

import DesignElevatorSystem.Direction;
import DesignElevatorSystem.elevatorCar.ElevatorCar;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SimpleElevatorSelectionStrategy implements ElevatorSelectionStrategy{

    @Override
    public Optional<ElevatorCar> findElevator(Direction direction, int floor, List<ElevatorCar> elevators) {
        Map<Direction, List<ElevatorCar>> directionCarMap = elevators.stream().collect(Collectors.groupingBy(ElevatorCar::getDirection));
//        directionCarMap.get(direction).stream().filter(car -> car.getFloor())
        return elevators.stream().findAny();
    }
}
