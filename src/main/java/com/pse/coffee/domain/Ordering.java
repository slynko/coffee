package com.pse.coffee.domain;

import com.pse.coffee.domain.catalogue.Recipe;
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

    private final OrderPreparation addOrder;
    private final Stock stock;
    private final Catalogue catalogue;

    public OrderResult handleUserCommand(Order order) {
        LOG.info(format("Domain: Start command handling: %s", order));

        final DrinkName drinkName = order.getDrinkName();
        final Recipe recipe = catalogue.getIngredientsFor(drinkName);
        final Set<Ingredient> missingIngredients = recipe.getIngredients().entrySet().stream()
                .filter(ingredient -> !stock.hasEnoughOf(ingredient.getKey(), ingredient.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        if (!missingIngredients.isEmpty()) {
            return OrderResult.INGREDIENT_MISSING;
        }
        addOrder.addOrder(order);
        LOG.info(format("Domain: Finish command handling: %s", order));
        return OrderResult.OK;
    }
}
