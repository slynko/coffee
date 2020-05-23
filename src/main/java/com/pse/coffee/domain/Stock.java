package com.pse.coffee.domain;

/**
 * Driven Port for stock related operations.
 */
public interface Stock {

    /**
     * Check if has enough of the ingredient.
     */
    boolean hasEnoughOf(final IngredientMeasurable ingredientMeasurable);
}
