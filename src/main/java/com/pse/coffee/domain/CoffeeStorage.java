package com.pse.coffee.domain;

import com.pse.commons.HexagonalArchitecture;

import static com.pse.commons.HexagonalArchitecture.Port.Type.DRIVEN;

@HexagonalArchitecture.Port(DRIVEN)
public interface CoffeeStorage {

    /**
     * Fetch coffee types.
     */
    boolean isAvailable(final DrinkName drinkName);
}
