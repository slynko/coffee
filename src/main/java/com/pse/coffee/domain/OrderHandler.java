package com.pse.coffee.domain;

import com.pse.coffee.domain.model.CoffeeOrder;

/**
 * Driven Port for adding coffee orders.
 */
public interface OrderHandler {

    /**
     * Add coffee order.
     */
    void addOrder(final CoffeeOrder coffeeOrder);
}
