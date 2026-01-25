package DesignElevatorSystem.elevatorObserver;

import DesignElevatorSystem.elevatorCar.ElevatorCar;

public interface ElevatorObserver {
    public void notify(ElevatorCar elevator);
}
