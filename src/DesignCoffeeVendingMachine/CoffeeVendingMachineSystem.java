package DesignCoffeeVendingMachine;

import DesignCoffeeVendingMachine.coffee.Coffee;
import DesignCoffeeVendingMachine.coffee.CoffeeFactory;
import DesignCoffeeVendingMachine.coffee.CoffeeType;
import DesignCoffeeVendingMachine.coffee.decorator.ApplyCoffeeDecorator;
import DesignCoffeeVendingMachine.coffee.decorator.DecoratorType;
import DesignCoffeeVendingMachine.inventory.Ingredient;
import DesignCoffeeVendingMachine.inventory.Inventory;
import DesignCoffeeVendingMachine.state.ReadyState;
import DesignCoffeeVendingMachine.state.VendingMachineState;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CoffeeVendingMachineSystem {
    Inventory inventory;
    @Setter
    Coffee coffee;
    @Getter
    @Setter
    VendingMachineState state;
    CoffeeFactory coffeeFactory;
    ApplyCoffeeDecorator coffeeDecorator;

    public CoffeeVendingMachineSystem() {
        Map<Ingredient, Integer> inventory = Map.of(
                new Ingredient("Caramel"), 100,
                new Ingredient("CoffeeBeans"), 100,
                new Ingredient("Sugar"), 100,
                new Ingredient("Milk"), 200
        );
        this.inventory = new Inventory(inventory);
        state = new ReadyState(this);
        coffeeFactory = new CoffeeFactory();
        coffeeDecorator = new ApplyCoffeeDecorator();
    }

    public void createAndSetCoffee(CoffeeType coffeeType, List<DecoratorType> decorators) {
        Coffee coffee = coffeeFactory.createCoffee(coffeeType);
        for (DecoratorType decoratorType : decorators) {
            coffee = coffeeDecorator.applyCoffeeDecorator(decoratorType, coffee);
        }
        this.setCoffee(coffee);
    }

    public boolean isInventoryAvailableForCoffee() {
        if (coffee == null) return false;
        return this.inventory.isInventoryAvailable(coffee.getIngredients());
    }

    public boolean deductInventoryForCoffee() {
        if (coffee == null) return false;
        return this.inventory.deductInventory(coffee.getIngredients());
    }

    public int getCoffeePrice() {
        return coffee != null ? coffee.getPrice() : 0;
    }

    public void insertCash(int cash) {
        this.state.insertCash(cash);
    }

    public void selectCoffee(CoffeeType coffeeType, List<DecoratorType> decorators) {
        this.state.selectCoffee(coffeeType, decorators);
    }

    public void dispenseCoffee() {
        this.state.dispenseCoffee();
    }

    public static void main(String[] args) {
        CoffeeVendingMachineSystem coffeeVendingMachineSystem = new CoffeeVendingMachineSystem();
        coffeeVendingMachineSystem.selectCoffee(CoffeeType.LATTE, Arrays.asList(DecoratorType.CARAMEL));
        coffeeVendingMachineSystem.dispenseCoffee();
        coffeeVendingMachineSystem.insertCash(100000);
        coffeeVendingMachineSystem.dispenseCoffee();

        coffeeVendingMachineSystem.selectCoffee(CoffeeType.ESPRESSO, Arrays.asList(DecoratorType.CARAMEL));
    }
}
