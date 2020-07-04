package com.pse.coffee.infra.driving.order;

import com.pse.coffee.domain.CustomerOrderHandler;
import com.pse.coffee.domain.Order;
import org.junit.jupiter.api.Test;

import static com.pse.coffee.domain.DrinkName.ESPRESSO;
import static com.pse.coffee.domain.OrderResult.OK;
import static com.pse.commons.HexagonalArchitectureConditions.aLeftAdapter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CustomerOrderControllerTest {

    private final CustomerOrderHandler service = mock(CustomerOrderHandler.class);
    private final CustomerOrderController adapter = new CustomerOrderController(service);

    @Test
    void should_be_a_left_adapter() {
        assertThat(CustomerOrderController.class).is(aLeftAdapter());
    }

    @Test
    void should_delegate_user_order_processing_to_domain() {
        final Order order = Order.builder()
                .personName("Vincent")
                .drink(ESPRESSO)
                .quantity(2)
                .build();
        given(service.process(order)).willReturn(OK);

        assertThat(adapter.processOrder(order)).isEqualTo("OK");
    }

    @Test
    void should_fail_to_process_null_user_order() {
        assertThatNullPointerException()
                .isThrownBy(() -> adapter.processOrder(null));
    }

}
