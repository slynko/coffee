package com.pse.coffee.domain;

/**
 * Driven Port for stock related operations.
 */
public interface Stock {

    boolean hasEnoughOf(final IngredientMeasurable ingredientMeasurable);

}
