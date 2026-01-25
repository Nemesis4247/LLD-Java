package DesignInventoryManagementSystem.warehouseSelectionStrategy;

import DesignInventoryManagementSystem.Address;
import DesignInventoryManagementSystem.warehouse.Warehouse;

import java.util.List;
import java.util.Optional;

public class SimpleWarehouseSelectionStrategy implements WarehouseSelectionStrategy {
    @Override
    public Optional<Warehouse> getWarehouse(List<Warehouse> warehouseList, Address userAddress) {
        return Optional.of(warehouseList.get(0));
    }
}
