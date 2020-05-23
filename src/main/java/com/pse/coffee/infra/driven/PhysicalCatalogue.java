package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

/**
 * Driven Port Adapter for catalog related operations.
 */
@Service
public class PhysicalCatalogue implements Catalogue {
    private final static Logger LOG = LoggerFactory.getLogger(PhysicalCatalogue.class);

    @Override
    public Set<IngredientMeasurable> getIngredientsFor(DrinkName drinkName) {
        LOG.info(format("Driven Port Adapter: Get ingredients for: %s", drinkName.name()));

        final Set<IngredientMeasurable> ingredients = new HashSet<>();
        switch (drinkName) {
            case LATTE:
                ingredients.add(new IngredientMeasurable(Ingredient.COFFEE_BEANS, 7));
                ingredients.add(new IngredientMeasurable(Ingredient.MILK, 5));
                break;
            case ESPRESSO:
                ingredients.add(new IngredientMeasurable(Ingredient.COFFEE_BEANS, 10));
        }
        return ingredients;
    }
}
