package com.pse.coffee.infra.driving.order;

import com.pse.coffee.domain.CustomerOrderHandler;
import com.pse.coffee.domain.Invoice;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import static com.pse.coffee.domain.DrinkName.ESPRESSO;
import static com.pse.commons.HexagonalArchitectureConditions.aLeftAdapter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.joda.money.CurrencyUnit.EUR;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CustomerOrderControllerTest {

    private final CustomerOrderHandler customerOrderHandler = mock(CustomerOrderHandler.class);
    private final CustomerOrderController adapter = new CustomerOrderController(customerOrderHandler);

    @Test
    void should_be_a_left_adapter() {
        assertThat(CustomerOrderController.class).is(aLeftAdapter());
    }

    @Test
    void should_delegate_user_order_processing_to_domain() {
        final OrderDto order = new OrderDto( ESPRESSO, 2, "Vincent");
        final Invoice invoice = Invoice.builder()
                .drink(ESPRESSO)
                .quantity(2)
                .unitCost(Money.of(EUR, 10))
                .build();
        given(customerOrderHandler.process(order.toDomain())).willReturn(invoice);

        assertThat(adapter.processOrder(order)).isEqualTo(InvoiceDto.from(invoice));
    }

    @Test
    void should_fail_to_process_null_user_order() {
        assertThatNullPointerException()
                .isThrownBy(() -> adapter.processOrder(null));
    }

}
