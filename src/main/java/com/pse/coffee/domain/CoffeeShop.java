package com.pse.coffee.domain;

/**
 * Domain which uses driven ports for handling coffee operations.
 */
public class CoffeeShop implements UserCommand{
    private AddCoffeeOrder addCoffeeOrder;
    private FetchCoffee fetchCoffee;

    public CoffeeShop(AddCoffeeOrder addCoffeeOrder, FetchCoffee fetchCoffee) {
        this.addCoffeeOrder = addCoffeeOrder;
        this.fetchCoffee = fetchCoffee;
    }

    public void handleUserCommand(Object command) {
        final Object coffeeTypes = fetchCoffee.fetchCoffeeTypes(null);

        addCoffeeOrder.addCoffeeOrder(null);
    }
}
