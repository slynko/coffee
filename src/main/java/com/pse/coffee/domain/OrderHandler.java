package com.pse.coffee.domain;

import com.pse.commons.HexagonalArchitecture;

import static com.pse.commons.HexagonalArchitecture.Port.Type.DRIVEN;

@HexagonalArchitecture.Port(DRIVEN)
public interface OrderHandler {

    /**
     * Add coffee order.
     */
    void addOrder(final CoffeeOrder coffeeOrder);
}
