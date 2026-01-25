package DesignInventoryManagementSystem.controllers;

import DesignInventoryManagementSystem.product.Product;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ProductSearchController {
    Map<String, List<Product>> productIndex;
    private static ProductSearchController instance;

    public static ProductSearchController getInstance() {
        synchronized (ProductSearchController.class) {
            if (instance == null) {
                instance = new ProductSearchController();
            }
        }
        return instance;
    }

    public List<Product> getAllProducts() {
        return productIndex.values().stream().flatMap(Collection::stream).toList();
    }

    public List<Product> getProductByName(String name) {
        return productIndex.get(name);
    }
}
