package com.pse.coffee.domain;

import com.pse.coffee.domain.catalogue.CatalogueItem;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;


/**
 * Domain logic which uses driven ports for handling coffee operations.
 */
@AllArgsConstructor
public class Ordering implements CommandHandler {
    private final static Logger LOG = LoggerFactory.getLogger(Ordering.class);

    private final OrderPreparation preparation;
    private final Stock stock;
    private final Catalogue catalogue;

    public OrderResult handleUserCommand(PreparationDemand order) {
        LOG.info(format("Domain: Start command handling: %s", order));

        final DrinkName drinkName = order.getDrinkName();
        final CatalogueItem catalogueItem = catalogue.getItemFor(drinkName);
        final Set<Ingredient> missingIngredients = catalogueItem.getRecipe().getIngredients().entrySet().stream()
                .filter(ingredient -> !stock.hasEnoughOf(ingredient.getKey(), ingredient.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        if (!missingIngredients.isEmpty()) {
            return OrderResult.INGREDIENT_MISSING;
        }
        preparation.addOrder(order);
        LOG.info(format("Domain: Finish command handling: %s", order));
        return OrderResult.OK;
    }
}
