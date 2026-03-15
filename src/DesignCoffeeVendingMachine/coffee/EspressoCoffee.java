package DesignCoffeeVendingMachine.coffee;

import DesignCoffeeVendingMachine.inventory.Ingredient;

import java.util.Map;

public class EspressoCoffee implements Coffee {
    static int PRICE = 1000;
    static Map<Ingredient, Integer> INGREDIENTS = Map.of(new Ingredient("CoffeeBeans"), 100,
            new Ingredient("Milk"), 100);

    public CoffeeType getCoffeeType() {
        return CoffeeType.ESPRESSO;
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public Map<Ingredient, Integer> getIngredients() {
        return INGREDIENTS;
    }
}
