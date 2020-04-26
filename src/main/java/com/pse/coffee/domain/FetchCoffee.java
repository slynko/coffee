package com.pse.coffee.domain;

/**
 * Driven Port for coffee related fetch operations.
 */
public interface FetchCoffee {

    /**
     * Fetch coffee types.
     */
    Object fetchCoffeeTypes(final Object coffeeTypesRequest);
}
