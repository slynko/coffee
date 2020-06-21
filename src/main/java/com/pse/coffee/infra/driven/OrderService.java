package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.PreparationDemand;
import com.pse.coffee.domain.OrderPreparation;
import com.pse.commons.HexagonalArchitecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@HexagonalArchitecture.RightAdapter
public class OrderService implements OrderPreparation {
    private final static Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Override
    public void addOrder(PreparationDemand coffeeOrder) {
        LOG.info(format("Right Adapter: Add order: %s", coffeeOrder));
    }
}
