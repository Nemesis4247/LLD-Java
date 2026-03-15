package DesignCoffeeVendingMachine.coffee;

import DesignCoffeeVendingMachine.inventory.Ingredient;

import java.util.Map;

public class CoffeeFactory {

    public Coffee createCoffee(CoffeeType coffeeType) {
        return switch (coffeeType) {
            case ESPRESSO -> new EspressoCoffee();
            case LATTE -> new LatteCoffee();
        };
    }
}
