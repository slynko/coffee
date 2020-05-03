package com.pse.coffee.domain;

import com.pse.coffee.domain.model.CoffeeType;

/**
 * Driven Port for coffee related fetch operations.
 */
public interface CoffeeStorage {

    /**
     * Fetch coffee types.
     */
    boolean isAvailable(final CoffeeType coffeeType);
}
