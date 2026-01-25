package DesignInventoryManagementSystem.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Product {
    List<ProductUnit> units;
    double price;
    String name;
    int productId;
    ProductCategory category;
}
