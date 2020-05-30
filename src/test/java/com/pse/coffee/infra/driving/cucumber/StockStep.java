package com.pse.coffee.infra.driving.cucumber;

import com.pse.coffee.domain.CoffeeStorage;
import com.pse.coffee.infra.driven.CoffeeService;
import io.cucumber.java8.En;

public final class StockStep implements En {

    private final World world;
    private final CoffeeStorage coffeeStorage;

    public StockStep(final World world,
                     final CoffeeService coffeeStorage) {
        this.world = world;
        this.coffeeStorage = coffeeStorage;
        declareSteps();
    }

    private enum Ingredient { //TODO: use actual object from the domain
        MILK, COFFEE
    }

    private void declareSteps() {
        Given("{word} is available", this::ingredientIsAvailableHint);
        Given("{word} is not available", this::ingredientIsUnavailableHint);
    }

    private void ingredientIsAvailableHint(final String ingredientName) {
        final Ingredient ingredient = Ingredient.valueOf(ingredientName.toUpperCase());
        switch (ingredient) {
            case MILK:
                world.milkIsAvailable = true;
                break;
            case COFFEE:
                world.coffeeIsAvailable = true;
                break;
            default:
                throw new IllegalArgumentException("Unexpected ingredient: " + ingredient);
        }
        //TODO: stock.updateStock(MILK, centiliters(10000))
        //TODO: stock.updateStock(COFFEE, grams(10000))
    }

    private void ingredientIsUnavailableHint(final String ingredientName) {
        final Ingredient ingredient = Ingredient.valueOf(ingredientName.toUpperCase());
        switch (ingredient) {
            case MILK:
                world.milkIsAvailable = false;
                break;
            case COFFEE:
                world.coffeeIsAvailable = false;
                break;
            default:
                throw new IllegalArgumentException("Unexpected ingredient: " + ingredient);
        }
        //TODO: stock.updateStock(MILK, centiliters(0))
        //TODO: stock.updateStock(COFFEE, grams(0))
    }
}
