package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.OrderPreparation;
import com.pse.coffee.domain.PreparationDemand;
import com.pse.commons.HexagonalArchitecture;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@HexagonalArchitecture.RightAdapter
public final class OrderService implements OrderPreparation {
    @Override
    public void addOrder(@NonNull final PreparationDemand coffeeOrder) {
        log.info("Right Adapter: Add order: {}", coffeeOrder);
    }
}
