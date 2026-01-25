package DesignInventoryManagementSystem.warehouseSelectionStrategy;

import DesignInventoryManagementSystem.Address;
import DesignInventoryManagementSystem.warehouse.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseSelectionStrategy {

    public Optional<Warehouse> getWarehouse(List<Warehouse> warehouseList, Address userAddress);

}
