package DesignInventoryManagementSystem.warehouse;

import DesignInventoryManagementSystem.Address;
import lombok.Data;

import java.util.Map;

@Data
public class Warehouse {
    Inventory inventory;
    Address location;
    int warehouseId;

    public boolean removeItems(Map<Integer, Integer> productCountMap) {
        return inventory.removeItems(productCountMap);
    }
}
