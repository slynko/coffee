package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.CoffeeShop;
import com.pse.coffee.domain.OrderHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pse.coffee.domain.CoffeeOrder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * Driven Port Adapter for adding coffee orders.
 */
@Service
public class OrderService implements OrderHandler {
    private final static Logger LOG = LoggerFactory.getLogger(CoffeeShop.class);

    // todo add sqlRepository for adding

    public void addOrder(CoffeeOrder coffeeOrder) {
        LOG.info(format("Driven Port Adapter: Add order: %s", coffeeOrder));
    }
}
