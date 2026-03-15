package DesignCoffeeVendingMachine.state;

import DesignCoffeeVendingMachine.CoffeeVendingMachineSystem;
import DesignCoffeeVendingMachine.coffee.CoffeeType;
import DesignCoffeeVendingMachine.coffee.decorator.DecoratorType;

import java.util.List;

public class ReadyState implements VendingMachineState {
    CoffeeVendingMachineSystem coffeeVendingMachineSystem;

    public ReadyState(CoffeeVendingMachineSystem coffeeVendingMachineSystem) {
        this.coffeeVendingMachineSystem = coffeeVendingMachineSystem;
    }

    @Override
    public void insertCash(int cash) {
        System.out.println("Select coffee first");
    }

    @Override
    public void selectCoffee(CoffeeType coffeeType, List<DecoratorType> decorators) {
        this.coffeeVendingMachineSystem.createAndSetCoffee(coffeeType, decorators);
        boolean isInventoryAvailable = coffeeVendingMachineSystem.isInventoryAvailableForCoffee();
        if (isInventoryAvailable) {
            System.out.println("Coffee Available, proceeding to payment");
            this.coffeeVendingMachineSystem.setState(new CoffeeSelectedState(coffeeVendingMachineSystem));
        } else {
            System.out.println("Coffee not available, please try something else");
        }
    }

    @Override
    public void dispenseCoffee() {
        System.out.println("Select coffee first");
    }
}
