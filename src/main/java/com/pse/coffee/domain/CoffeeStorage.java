package com.pse.coffee.domain;

/**
 * Driven Port for coffee related fetch operations.
 */
public interface CoffeeStorage {

    /**
     * Fetch coffee types.
     */
    boolean isAvailable(final CoffeeType coffeeType);
}
