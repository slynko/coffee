package com.pse.coffee.infra.driving.order;

import com.pse.coffee.domain.CustomerOrderHandlerApi;
import com.pse.commons.architecture.HexagonalArchitecture;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@HexagonalArchitecture.LeftAdapter
@Slf4j
@AllArgsConstructor
public final class CustomerOrderController {
    private final CustomerOrderHandlerApi userCommand;

    public InvoiceDto processOrder(final OrderDto order) {
        log.info("Left Adapter: Start order processing: {}", order);
        final InvoiceDto invoice = InvoiceDto.from(
                userCommand.process(order.toDomain())
        );
        log.info("Left Adapter: Finish order processing: {}", invoice);
        return invoice;
    }
}
