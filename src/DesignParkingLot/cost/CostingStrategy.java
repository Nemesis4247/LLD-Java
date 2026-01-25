package DesignParkingLot.cost;

import DesignParkingLot.Ticket;

import java.time.LocalDateTime;

public interface CostingStrategy {
    public double getCost(Ticket ticket, LocalDateTime exitTS);
}
