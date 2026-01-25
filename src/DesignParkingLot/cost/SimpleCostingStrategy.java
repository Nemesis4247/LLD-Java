package DesignParkingLot.cost;

import DesignParkingLot.Ticket;
import DesignParkingLot.VechicleType;

import java.time.Duration;
import java.time.LocalDateTime;

public class SimpleCostingStrategy implements CostingStrategy {

    private final static double BIKE_HOURLY_RENTAL = 50.0;
    private final static double CAR_HOURLY_RENTAL = 100.0;
    private final static double DEFAULT_HOURLY_RENTAL = 100.0;

    @Override
    public double getCost(Ticket ticket, LocalDateTime exitTS) {
        long delta = Duration.between(ticket.getEntryTime(), exitTS).toHours();
        delta = 2; // demo
        if (VechicleType.BIKE.equals(ticket.getVechicle().getVechicleType())) {
            return BIKE_HOURLY_RENTAL * delta;
        } else if (VechicleType.CAR.equals(ticket.getVechicle().getVechicleType())) {
            return CAR_HOURLY_RENTAL * delta;
        } else {
            return DEFAULT_HOURLY_RENTAL * delta;
        }
    }
}
