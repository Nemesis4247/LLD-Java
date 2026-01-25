package DesignInventoryManagementSystem.controllers;

import DesignInventoryManagementSystem.user.User;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    Map<Integer, User> userMap;

    public UserController() {
        userMap = new HashMap<>();
    }

    public User getUser(int userId) {
        return userMap.get(userId);
    }
}
