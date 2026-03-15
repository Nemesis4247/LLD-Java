package DesignCoffeeVendingMachine.coffee;

import DesignCoffeeVendingMachine.inventory.Ingredient;
import java.util.Map;

public interface Coffee {

    public CoffeeType getCoffeeType();

    public abstract int getPrice();

    public abstract Map<Ingredient, Integer> getIngredients();
}
