package DesignCoffeeVendingMachine.coffee.decorator;

import DesignCoffeeVendingMachine.coffee.Coffee;
import DesignCoffeeVendingMachine.inventory.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class ExtraSugarCoffeeDecorator extends CoffeeDecorator {
    static int PRICE = 50;
    static Map<Ingredient, Integer> INGREDIENTS = Map.of(new Ingredient("Sugar"), 100);

    public ExtraSugarCoffeeDecorator(Coffee coffee) {
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
