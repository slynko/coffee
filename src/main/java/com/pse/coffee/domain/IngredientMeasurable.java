package com.pse.coffee.domain;

import static java.util.Objects.requireNonNull;

public class IngredientMeasurable {
    private final Ingredient ingredient;
    private final int quantity;

    public IngredientMeasurable(Ingredient ingredient, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be less or equal 0");
        }
        this.ingredient = requireNonNull(ingredient, "ingredient");
        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

}
