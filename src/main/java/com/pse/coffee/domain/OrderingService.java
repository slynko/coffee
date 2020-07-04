package com.pse.coffee.domain;

import com.pse.coffee.domain.catalogue.Catalogue;
import com.pse.coffee.domain.catalogue.CatalogueItem;
import com.pse.coffee.domain.preparation.Drink;
import com.pse.coffee.domain.preparation.OrderPreparation;
import com.pse.coffee.domain.recipe.Ingredient;
import com.pse.coffee.domain.recipe.Quantity;
import com.pse.coffee.domain.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static java.util.stream.IntStream.range;

@Slf4j
@AllArgsConstructor
public final class OrderingService implements CustomerOrderHandler {

    private final OrderPreparation preparation;
    private final Stock stock;
    private final Catalogue catalogue;

    public Invoice process(@NonNull final Order order) {
        log.info("Domain: Start order processing: {}", order);

        final DrinkName drink = order.getDrink();
        final CatalogueItem catalogueItem = catalogue.find(drink)
                .orElseThrow(() -> new UnknownDrinkException(drink));
        checkForMissingIngredient(order, catalogueItem).ifPresent(missingIngredient -> {
            throw new UnavailableIngredientException(missingIngredient);
        });

        range(0, order.getQuantity()).forEach(any ->
                preparation.prepare(Drink.builder()
                        .name(drink)
                        .recipe(catalogueItem.getRecipe())
                        .personName(order.getPersonName())
                        .build())
        );
        log.info("Domain: Finish order processing: {}", order);
        return Invoice.builder()
                .drink(drink)
                .quantity(order.getQuantity())
                .unitCost(catalogueItem.getUnitCost())
                .build();
    }

    private Optional<Ingredient> checkForMissingIngredient(final Order order, final CatalogueItem catalogueItem) {
        return catalogueItem.getRecipe().getEntries().stream()
                .filter(recipeEntry -> !ingredientIsAvailable(recipeEntry, order.getQuantity()))
                .map(Recipe.Entry::getIngredient)
                .findAny();
    }

    private boolean ingredientIsAvailable(final Recipe.Entry recipeEntry, final int number) {
        final Ingredient ingredient = recipeEntry.getIngredient();
        final Quantity unitRequiredQuantity = recipeEntry.getRequiredQuantity();
        final Quantity requiredQuantity = unitRequiredQuantity.multipliedBy(number);
        return stock.hasEnoughOf(ingredient, requiredQuantity);
    }
}
