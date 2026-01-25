package DesignInventoryManagementSystem.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProductUnit {
    int serial;
    String name;
    Date expiryDate;
}
