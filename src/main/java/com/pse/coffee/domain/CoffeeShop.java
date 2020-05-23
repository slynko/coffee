package com.pse.coffee.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;


/**
 * Domain which uses driven ports for handling coffee operations.
 */
public class CoffeeShop implements CommandHandler {
    private final static Logger LOG = LoggerFactory.getLogger(CoffeeShop.class);

    private final OrderHandler addCoffeeOrder;
    private final CoffeeStorage fetchCoffee;

    public CoffeeShop(OrderHandler addCoffeeOrder, CoffeeStorage fetchCoffee) {
        this.addCoffeeOrder = addCoffeeOrder;
        this.fetchCoffee = fetchCoffee;
    }

    public void handleUserCommand(CoffeeOrder coffeeOrder) {
        final CoffeeType coffeeType = coffeeOrder.getCoffeeType();
        LOG.info(format("Domain: Start command handling: %s", coffeeOrder));
        final boolean isAvailable = fetchCoffee.isAvailable(coffeeType);

        if (isAvailable) {
            addCoffeeOrder.addOrder(coffeeOrder);
        } else {
            // todo: return an error code or throw an exception
        }
        LOG.info(format("Domain: Finish command handling: %s", coffeeOrder));
    }
}
