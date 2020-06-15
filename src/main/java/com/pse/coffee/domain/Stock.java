package com.pse.coffee.domain;

import com.pse.coffee.domain.catalogue.Quantity;

/**
 * Driven Port for stock related operations.
 */
public interface Stock {

    boolean hasEnoughOf(final Ingredient ingredientMeasurable, Quantity quantity);

}
