package DesignInventoryManagementSystem.user;

import DesignInventoryManagementSystem.Address;
import lombok.Getter;

@Getter
public class User {
    int userId;
    String name;
    String passHash;
    Cart userCart;
    Address address;

    public User(int userId, String name, String passHash) {
        userCart = new Cart();
    }
}
