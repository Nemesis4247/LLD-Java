package DesignCoffeeVendingMachine.coffee.decorator;

import DesignCoffeeVendingMachine.coffee.Coffee;

public class ApplyCoffeeDecorator {
    public Coffee applyCoffeeDecorator(DecoratorType decoratorType, Coffee coffee) {
        return switch (decoratorType) {
            case EXTRA_SUGAR -> new ExtraSugarCoffeeDecorator(coffee);
            case CARAMEL -> new CaramelCoffeeDecorator(coffee);
        };
    }
}
