package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.*;
import com.pse.coffee.domain.catalogue.*;
import org.joda.money.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.pse.coffee.domain.DrinkName.ESPRESSO;
import static com.pse.coffee.domain.DrinkName.LATTE;
import static com.pse.coffee.domain.catalogue.Unit.CL;
import static com.pse.coffee.domain.catalogue.Unit.GRAM;
import static java.lang.String.format;
import static org.joda.money.CurrencyUnit.EUR;

/**
 * Driven Port Adapter for catalog related operations.
 */
@Service
public class PhysicalCatalogue implements Catalogue {
    private final static Logger LOG = LoggerFactory.getLogger(PhysicalCatalogue.class);

    @Override
    public CatalogueItem getItemFor(DrinkName drinkName) {
        LOG.info(format("Driven Port Adapter: Get ingredients for: %s", drinkName.name()));

        final Map<Ingredient, Quantity> ingredients = new HashMap<>();
        switch (drinkName) {
            case LATTE:
                return new CatalogueItem(LATTE, Money.of(EUR, 5),
                        Recipe.builder()
                                .ingredient(Ingredient.COFFEE_BEANS, new Quantity(7, GRAM))
                                .ingredient(Ingredient.MILK, new Quantity(5, CL))
                                .build());
            case ESPRESSO:
                return new CatalogueItem(ESPRESSO, Money.of(EUR, 3),
                        Recipe.builder()
                                .ingredient(Ingredient.COFFEE_BEANS, new Quantity(10, GRAM))
                                .build());
        }
        throw new IllegalArgumentException(format("No drink name found: %s", drinkName.name()));
    }
}
