package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.OrderHandler;
import com.pse.coffee.domain.model.CoffeeOrder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * Driven Port Adapter for adding coffee orders.
 */
@Service
public class OrderService implements OrderHandler {
    private final static Logger LOG = Logger.getLogger(OrderService.class);

    // todo add sqlRepository for adding

    public void addOrder(CoffeeOrder coffeeOrder) {
        LOG.info(format("Driven Port Adapter: Add order: %s", coffeeOrder));
    }
}
