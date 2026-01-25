package DesignParkingLot;

import DesignParkingLot.cost.CostingStrategy;
import DesignParkingLot.park.ParkingStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingManager {
    private ParkingStrategy parkingStrategy;
    private CostingStrategy costingStrategy;
}
