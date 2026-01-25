package DesignParkingLot;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Invoice {
    private Ticket ticket;
    private LocalDateTime exit;
    private double cost;
}
