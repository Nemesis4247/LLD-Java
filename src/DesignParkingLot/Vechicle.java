package DesignParkingLot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vechicle {
    private String id;
    private VechicleType vechicleType;
}
