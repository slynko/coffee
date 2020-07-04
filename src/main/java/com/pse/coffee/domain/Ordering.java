package com.pse.coffee.domain;

import com.pse.coffee.domain.catalogue.CatalogueItem;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Domain logic which uses driven ports for handling coffee operations.
 */
@Slf4j
@AllArgsConstructor
public final class Ordering implements CommandHandler {

    private final OrderPreparation preparation;
    private final Stock stock;
    private final Catalogue catalogue;

    public OrderResult handleUserCommand(@NonNull final PreparationDemand order) {
        log.info("Domain: Start command handling: {}", order);

        final DrinkName drink = order.getDrink();
        final CatalogueItem catalogueItem = catalogue.getItemFor(drink);
        final Set<Ingredient> missingIngredients = catalogueItem.getRecipe().getIngredients().entrySet().stream()
                .filter(ingredient -> !stock.hasEnoughOf(ingredient.getKey(), ingredient.getValue().times(order.getQuantity())))
                .map(Map.Entry::getKey)
                .collect(toSet());

        if (!missingIngredients.isEmpty()) {
            return OrderResult.INGREDIENT_MISSING;
        }
        preparation.addOrder(order);
        log.info("Domain: Finish command handling: {}", order);
        return OrderResult.OK;
    }
}
