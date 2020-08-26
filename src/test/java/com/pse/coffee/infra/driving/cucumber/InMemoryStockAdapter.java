package com.pse.coffee.infra.driving.cucumber;

import com.pse.coffee.domain.StockSpi;
import com.pse.coffee.domain.recipe.Ingredient;
import com.pse.coffee.domain.recipe.Quantity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStockAdapter implements StockSpi {

    private final Map<Ingredient, Boolean> ingredients = new ConcurrentHashMap<>();

    public void setAvailability(final Ingredient ingredient, final boolean availability) {
        ingredients.put(ingredient, availability);
    }

    @Override
    public boolean hasEnoughOf(final Ingredient ingredient, final Quantity requiredQuantity) {
        return ingredients.get(ingredient);
    }
}
