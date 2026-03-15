package DesignCoffeeVendingMachine.coffee.decorator;

import DesignCoffeeVendingMachine.coffee.Coffee;
import DesignCoffeeVendingMachine.coffee.CoffeeType;
import DesignCoffeeVendingMachine.inventory.Ingredient;

import java.util.Map;

public abstract class CoffeeDecorator implements Coffee {
    Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    @Override
    public CoffeeType getCoffeeType() {
        return decoratedCoffee.getCoffeeType();
    }

    @Override
    public int getPrice() {
        return decoratedCoffee.getPrice();
    }

    @Override
    public Map<Ingredient, Integer> getIngredients() {
        return decoratedCoffee.getIngredients();
    }
}
