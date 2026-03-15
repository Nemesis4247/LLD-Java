package DesignCoffeeVendingMachine.state;

import DesignCoffeeVendingMachine.CoffeeVendingMachineSystem;
import DesignCoffeeVendingMachine.coffee.CoffeeType;
import DesignCoffeeVendingMachine.coffee.decorator.DecoratorType;

import java.util.List;

public class PaidState implements VendingMachineState {
    CoffeeVendingMachineSystem coffeeVendingMachineSystem;

    public PaidState(CoffeeVendingMachineSystem coffeeVendingMachineSystem) {
        this.coffeeVendingMachineSystem = coffeeVendingMachineSystem;
    }

    @Override
    public void insertCash(int cash) {
        System.out.println("Already paid");
    }

    @Override
    public void selectCoffee(CoffeeType coffeeType, List<DecoratorType> decorators) {
        System.out.println("Already selected");
    }

    @Override
    public void dispenseCoffee() {
        boolean isSuccessful = coffeeVendingMachineSystem.deductInventoryForCoffee();
        System.out.println("Coffee dispensed, please enjoy ur coffee :)");
        coffeeVendingMachineSystem.setCoffee(null);
        coffeeVendingMachineSystem.setState(new ReadyState(coffeeVendingMachineSystem));
    }
}
