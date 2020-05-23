package com.pse.coffee.domain;

import com.sun.org.apache.regexp.internal.REUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;


/**
 * Domain which uses driven ports for handling coffee operations.
 */
public class Ordering implements CommandHandler {
    private final static Logger LOG = LoggerFactory.getLogger(Ordering.class);

    private final OrderHandler addOrder;
    private final Stock stock;
    private final Catalogue catalogue;

    public Ordering(final OrderHandler addOrder,
                    final Stock stock,
                    final Catalogue catalogue) {
        this.addOrder = addOrder;
        this.stock = stock;
        this.catalogue = catalogue;
    }

    public Result handleUserCommand(Order order) {
        LOG.info(format("Domain: Start command handling: %s", order));

        final DrinkName drinkName = order.getDrinkName();
        final Set<IngredientMeasurable> ingredients = catalogue.getIngredientsFor(drinkName);
        final Set<IngredientMeasurable> missingIngredients = ingredients.stream()
                .filter(ingredient -> !stock.hasEnoughOf(ingredient))
                .collect(Collectors.toSet());

        if (!missingIngredients.isEmpty()) {
            return Result.INGREDIENT_MISSING;
        }
        addOrder.addOrder(order);
        LOG.info(format("Domain: Finish command handling: %s", order));
        return Result.OK;
    }
}
