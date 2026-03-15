package DesignCoffeeVendingMachine.state;

import DesignCoffeeVendingMachine.CoffeeVendingMachineSystem;
import DesignCoffeeVendingMachine.coffee.CoffeeType;
import DesignCoffeeVendingMachine.coffee.decorator.DecoratorType;

import java.util.List;

public class CoffeeSelectedState implements VendingMachineState {
    CoffeeVendingMachineSystem coffeeVendingMachineSystem;

    public CoffeeSelectedState(CoffeeVendingMachineSystem coffeeVendingMachineSystem) {
        this.coffeeVendingMachineSystem = coffeeVendingMachineSystem;
    }

    @Override
    public void insertCash(int cash) {
        if (cash < coffeeVendingMachineSystem.getCoffeePrice())
            System.out.println("Insufficient cash");
        else {
            System.out.println("Transaction successful");
            if (cash > coffeeVendingMachineSystem.getCoffeePrice())
                System.out.println("Dispensing change: " + (cash - coffeeVendingMachineSystem.getCoffeePrice()));
            coffeeVendingMachineSystem.setState(new PaidState(coffeeVendingMachineSystem));
        }
    }

    @Override
    public void selectCoffee(CoffeeType coffeeType, List<DecoratorType> decorators) {
        System.out.println("Coffee already selected");
    }

    @Override
    public void dispenseCoffee() {
        System.out.println("Select coffee first");
    }
}
