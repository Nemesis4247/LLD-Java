package DesignInventoryManagementSystem.warehouse;

import DesignInventoryManagementSystem.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inventory {
    List<Product> productList;

    public Inventory() {
        productList = new ArrayList<>();
    }

    public boolean addProduct(Product product) {
        return this.productList.add(product);
    }

    public boolean removeItems(Map<Integer, Integer> productCountMap) {
        // validate inv quantity
        productList.stream().forEach(p -> {
            if (productCountMap.containsKey(p.getProductId())) {
                for (int i=0; i < productCountMap.get(p.getProductId()); i++)
                    p.getUnits().remove(p.getUnits().size()-1);
            }
        });
        return true;
    }
}
