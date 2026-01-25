package DesignInventoryManagementSystem.order;

import DesignInventoryManagementSystem.user.User;
import lombok.Data;

import java.util.Map;

@Data
public class Order {
    User user;
    Map<Integer, Integer> items;
    OrderStatus orderStatus;
    Invoice invoice;
}
