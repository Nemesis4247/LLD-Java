package DesignCoffeeVendingMachine.state;

import DesignCoffeeVendingMachine.coffee.CoffeeType;
import DesignCoffeeVendingMachine.coffee.decorator.DecoratorType;

import java.util.List;

public interface VendingMachineState {
    void insertCash(int cash);
    void selectCoffee(CoffeeType coffeeType, List<DecoratorType> decorators);
    void dispenseCoffee();
}
