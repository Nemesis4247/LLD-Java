package DesignCoffeeVendingMachine.coffee.decorator;

import DesignCoffeeVendingMachine.coffee.Coffee;
import DesignCoffeeVendingMachine.inventory.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class CaramelCoffeeDecorator extends CoffeeDecorator {
    static int PRICE = 100;
    static Map<Ingredient, Integer> INGREDIENTS = Map.of(new Ingredient("Caramel"), 100);

    public CaramelCoffeeDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public int getPrice() {
        return decoratedCoffee.getPrice() + PRICE;
    }

    @Override
    public Map<Ingredient, Integer> getIngredients() {
        Map<Ingredient, Integer> res = new HashMap<>(decoratedCoffee.getIngredients());
        res.putAll(INGREDIENTS);
        return res;
    }
}
