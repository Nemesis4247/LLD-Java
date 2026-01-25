package DesignInventoryManagementSystem.controllers;

import DesignInventoryManagementSystem.order.Order;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OrderController {
    List<Order> orderList;
    Map<Integer, List<Order>> userIdToOrderList;
}
