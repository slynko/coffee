package com.pse.coffee.infra.driving.cucumber;

import com.pse.coffee.domain.recipe.Ingredient;
import io.cucumber.java8.En;

public final class StockSteps implements En {

    private final InMemoryStockAdapter stock;

    public StockSteps(final InMemoryStockAdapter stock) {
        this.stock = stock;
        declareSteps();
    }

    private void declareSteps() {
        Given("{word} is/are available", this::ingredientIsAvailableHint);
        Given("{word} is/are not available", this::ingredientIsUnavailableHint);
    }

    private void ingredientIsAvailableHint(final String ingredientName) {
        final Ingredient ingredient = Ingredient.valueOf(ingredientName.toUpperCase());
        stock.setAvailability(ingredient, true);
    }

    private void ingredientIsUnavailableHint(final String ingredientName) {
        final Ingredient ingredient = Ingredient.valueOf(ingredientName.toUpperCase());
        stock.setAvailability(ingredient, false);
    }
}
