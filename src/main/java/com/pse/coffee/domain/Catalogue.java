package com.pse.coffee.domain;

import com.pse.commons.HexagonalArchitecture;

import java.util.Set;

import static com.pse.commons.HexagonalArchitecture.Port.Type.DRIVEN;

@HexagonalArchitecture.Port(DRIVEN)
public interface Catalogue {

    /**
     * Get ingredients for a drink.
     */
    Set<IngredientMeasurable> getIngredientsFor(final DrinkName drink);
}
