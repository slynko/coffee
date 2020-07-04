package com.pse.coffee.infra.driven.preparation;

import org.junit.jupiter.api.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aRightAdapter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class OrderServiceTest {

    private final OrderService adapter = new OrderService();

    @Test
    void should_be_a_right_adapter() {
        assertThat(OrderService.class).is(aRightAdapter());
    }

    @Test
    void should_fail_to_add_null_order() {
        assertThatNullPointerException()
                .isThrownBy(() -> adapter.addOrder(null));
    }

}
