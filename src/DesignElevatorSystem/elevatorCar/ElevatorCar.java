package DesignElevatorSystem.elevatorCar;

import DesignElevatorSystem.Direction;
import DesignElevatorSystem.elevatorCar.requestHandlers.RequestHandlingStrategy;
import DesignElevatorSystem.elevatorObserver.ElevatorObserver;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ElevatorCar {
    private int id;
    private int floor;
    private Direction direction;
    private RequestHandlingStrategy requestHandler;
    List<ElevatorObserver> observers;

    public void move() {
        int newFloor = requestHandler.getNextStop(this);
        if (newFloor != floor) {
            Direction newDir = newFloor > floor ? Direction.UP : Direction.DOWN;
            this.setDirection(newDir);
            this.setFloor(newFloor);
            notifyObservers();
        }
    }

    private void notifyObservers() {
        observers.forEach(ElevatorObserver::notify);
    }
}
