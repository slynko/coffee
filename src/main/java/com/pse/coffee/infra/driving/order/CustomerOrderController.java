package com.pse.coffee.infra.driving.order;

import com.pse.coffee.domain.CustomerOrderHandlerApi;
import com.pse.coffee.domain.Invoice;
import com.pse.commons.architecture.HexagonalArchitecture;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@HexagonalArchitecture.LeftAdapter
@Slf4j
@AllArgsConstructor
public final class CustomerOrderController {
    private final CustomerOrderHandlerApi orderHandler;

    public InvoiceDto processOrder(final OrderDto order) {
        log.info("Left Adapter: Start order processing: {}", order);
        final Invoice invoice = orderHandler.process(order.toDomain());
        log.info("Left Adapter: Finish order processing: {}", invoice);
        return InvoiceDto.from(invoice);
    }
}
