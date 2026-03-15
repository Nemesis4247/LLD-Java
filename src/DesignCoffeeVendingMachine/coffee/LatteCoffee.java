package DesignCoffeeVendingMachine.coffee;

import DesignCoffeeVendingMachine.inventory.Ingredient;

import java.util.Map;

public class LatteCoffee implements Coffee {
    static int PRICE = 500;
    static Map<Ingredient, Integer> INGREDIENTS = Map.of(new Ingredient("CoffeeBeans"), 50,
            new Ingredient("Milk"), 200);

    public CoffeeType getCoffeeType() {
        return CoffeeType.LATTE;
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
