package com.pse.coffee.domain;

import com.pse.coffee.domain.recipe.Ingredient;

public final class UnavailableIngredientException extends RuntimeException {
    public UnavailableIngredientException(final Ingredient ingredient) {
        super("Unavailable ingredient: " + ingredient);
    }
}
