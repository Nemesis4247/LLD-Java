package DesignElevatorSystem.elevatorCar.requestHandlers;


import DesignElevatorSystem.Direction;
import DesignElevatorSystem.elevatorCar.ElevatorCar;

import java.util.PriorityQueue;
import java.util.TreeSet;

public class ScanRequestHandler implements RequestHandlingStrategy {
    private TreeSet<Integer> upQueue;
    private TreeSet<Integer> downQueue;
    public ScanRequestHandler() {
        upQueue = new TreeSet<>(Integer::compare);
        downQueue = new TreeSet<>((a,b) -> Integer.compare(b, a));
    }
    @Override
    public boolean handle(Direction direction, int floor) {
        if (Direction.UP.equals(direction)) {
            return upQueue.add(floor);
        } else {
            return downQueue.add(floor);
        }
    }

    @Override
    public int getNextStop(ElevatorCar elevatorCar) { // todo: fix
        if (!Direction.DOWN.equals(elevatorCar.getDirection()) && !upQueue.isEmpty()) {
            return upQueue.pollFirst();
        } else if (Direction.DOWN.equals(elevatorCar.getDirection()) && !downQueue.isEmpty()) {
            return downQueue.pollFirst();
        } else {
            return elevatorCar.getFloor();
        }
    }
}
