package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.CoffeeShop;
import com.pse.coffee.domain.CoffeeStorage;
import com.pse.coffee.domain.CoffeeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * Driven Port Adapter for coffee related fetch operations.
 */
@Service
public class CoffeeService implements CoffeeStorage {
    private final static Logger LOG = LoggerFactory.getLogger(CoffeeShop.class);

    // todo add sqlRepository for fetching
    
    public boolean isAvailable(CoffeeType coffeeType) {
        LOG.info(format("Driven Port Adapter: Verify if this type of coffe available: %s", coffeeType.name()));
        return true;
    }
}
