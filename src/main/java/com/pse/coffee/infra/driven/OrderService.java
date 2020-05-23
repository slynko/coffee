package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.Ordering;
import com.pse.coffee.domain.OrderHandler;
import com.pse.commons.HexagonalArchitecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pse.coffee.domain.Order;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@HexagonalArchitecture.RightAdapter
public class OrderService implements OrderHandler {
    private final static Logger LOG = LoggerFactory.getLogger(OrderService.class);

    // todo add sqlRepository for adding

    public void addOrder(CoffeeOrder coffeeOrder) {
        LOG.info(format("Right Adapter: Add order: %s", coffeeOrder));
    }
}
