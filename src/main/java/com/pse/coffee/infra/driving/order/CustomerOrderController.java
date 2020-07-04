package com.pse.coffee.infra.driving.order;

import com.pse.coffee.domain.CustomerOrderHandler;
import com.pse.coffee.domain.Invoice;
import com.pse.coffee.domain.Order;
import com.pse.commons.HexagonalArchitecture;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@HexagonalArchitecture.LeftAdapter
@Slf4j
@AllArgsConstructor
public final class CustomerOrderController {
    private final CustomerOrderHandler userCommand;

    public Invoice processOrder(@NonNull final Order order) {
        log.info("Left Adapter: Start order processing: {}", order);
        return userCommand.process(order);
    }
}
