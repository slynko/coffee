package com.pse.coffee.domain;

/**
 * Driven Port for adding coffee orders.
 */
public interface OrderHandler {

    /**
     * Add coffee order.
     */
    void addOrder(final CoffeeOrder coffeeOrder);
}
