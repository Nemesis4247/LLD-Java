package DesignInventoryManagementSystem.user;

import DesignInventoryManagementSystem.product.Product;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<Integer, Integer> productToQuantity;

    public Cart() {
        productToQuantity = new HashMap<>();
    }

    public boolean addToCart(int productId, int count) {
        productToQuantity.put(
                productId, productToQuantity.getOrDefault(productId, 0) + count);
        return true;
    }

    public boolean removeFromCart(int productId, int count) {
        productToQuantity.put(
                productId, productToQuantity.getOrDefault(productId, 0) - count);
        if (productToQuantity.get(productId).equals(0)) productToQuantity.remove(productId);
        return true;
    }
}
