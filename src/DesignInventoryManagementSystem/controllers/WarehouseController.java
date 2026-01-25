package DesignInventoryManagementSystem.controllers;

import DesignInventoryManagementSystem.Address;
import DesignInventoryManagementSystem.warehouse.Inventory;
import DesignInventoryManagementSystem.warehouse.Warehouse;
import DesignInventoryManagementSystem.warehouseSelectionStrategy.SimpleWarehouseSelectionStrategy;
import DesignInventoryManagementSystem.warehouseSelectionStrategy.WarehouseSelectionStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WarehouseController {
    List<Warehouse> warehouseList;
    WarehouseSelectionStrategy warehouseSelectionStrategy;
    static volatile WarehouseController instance;

    private WarehouseController() {
        warehouseList = new ArrayList<>();
        warehouseSelectionStrategy = new SimpleWarehouseSelectionStrategy();
    }

    public static WarehouseController getInstance() {
        synchronized (WarehouseController.class) {
            if (instance == null) {
                instance = new WarehouseController();
            }
        }
        return instance;
    }

    public boolean findWarehouseAndAdjustInventory(Map<Integer, Integer> items, Address userAddress) {
        Optional<Warehouse> warehouseOptional = this.warehouseSelectionStrategy.getWarehouse(this.warehouseList, userAddress);
        return warehouseOptional.map(warehouse -> {
            warehouse.getInventory().removeItems(items);
            return true;
        }).orElse(false);
    }

    public boolean addWarehouse(Warehouse warehouse) {
        return this.warehouseList.add(warehouse);
    }

    public boolean removeWarehouse(Warehouse warehouse) {
        return this.warehouseList.remove(warehouse);
    }
}
