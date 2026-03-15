package DesignCoffeeVendingMachine.inventory;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    Map<Ingredient, Integer> ingredients;

    public Inventory() {
        this.ingredients = new HashMap<>();
    }

    public Inventory(Map<Ingredient, Integer> ingredients) {
        this.ingredients = new HashMap<>(ingredients);
    }

    public boolean isInventoryAvailable(Map<Ingredient, Integer> recipe) {
        return recipe.entrySet().stream().noneMatch(
                e -> ingredients.getOrDefault(e.getKey(), 0) < e.getValue());
    }

    public synchronized boolean deductInventory(Map<Ingredient, Integer> recipe) {
        if (!isInventoryAvailable(recipe)) return false;
        recipe.forEach((i, q) -> ingredients.put(i, ingredients.get(i) - q));
        return true;
    }
}
