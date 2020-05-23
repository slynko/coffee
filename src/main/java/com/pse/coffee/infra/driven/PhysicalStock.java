package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * Driven Port Adapter for stock related operations.
 */
@Service
public class PhysicalStock implements Stock {
    private final static Logger LOG = LoggerFactory.getLogger(PhysicalStock.class);

    @Override
    public boolean hasEnoughOf(IngredientMeasurable ingredientMeasurable) {
        LOG.info(format("Driven Port Adapter: Verify if has enough of the ingredient: %s, quantity: %s", ingredientMeasurable.getIngredient().name(), ingredientMeasurable.getQuantity()));
        return true;
    }
}
