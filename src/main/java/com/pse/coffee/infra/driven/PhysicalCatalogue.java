package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.*;
import com.pse.coffee.domain.catalogue.Quantity;
import com.pse.coffee.domain.catalogue.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * Driven Port Adapter for catalog related operations.
 */
@Service
public class PhysicalCatalogue implements Catalogue {
    private final static Logger LOG = LoggerFactory.getLogger(PhysicalCatalogue.class);

    @Override
    public Recipe getIngredientsFor(DrinkName drinkName) {
        LOG.info(format("Driven Port Adapter: Get ingredients for: %s", drinkName.name()));

        final Map<Ingredient, Quantity> ingredients = new HashMap<>();
        switch (drinkName) {
            case LATTE:
                ingredients.put(Ingredient.COFFEE_BEANS, new Quantity(7, "g"));
                ingredients.put(Ingredient.MILK, new Quantity(5, "cl"));
                break;
            case ESPRESSO:
                ingredients.put(Ingredient.COFFEE_BEANS, new Quantity(10, "g"));
        }
        return new Recipe(ingredients);
    }
}
