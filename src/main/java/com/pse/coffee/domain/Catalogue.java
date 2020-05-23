package com.pse.coffee.domain;

import java.util.Set;

/**
 * Driven Port for coffee catalogue related operations.
 */
public interface Catalogue {

    /**
     * Check if has enough ingredients.
     */
    Set<IngredientMeasurable> getIngredientsFor(final DrinkName drinkName);
}
