package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.Ingredient;
import com.pse.coffee.domain.Stock;
import com.pse.coffee.domain.catalogue.Quantity;
import com.pse.commons.HexagonalArchitecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.pse.commons.HexagonalArchitecture.Port.Type.DRIVEN;
import static java.lang.String.format;

@HexagonalArchitecture.Port(DRIVEN)
@Service
public class PhysicalStock implements Stock {
    private final static Logger LOG = LoggerFactory.getLogger(PhysicalStock.class);

    @Override
    public boolean hasEnoughOf(Ingredient ingredient, Quantity quantity) {
        LOG.info(format("Driven Port Adapter: Verify if has enough of the ingredient: %s, quantity: %s", ingredient.name(), quantity));
        return true;
    }
}
