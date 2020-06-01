package com.pse.coffee.infra.driven;

import org.junit.jupiter.api.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aRightAdapter;
import static org.assertj.core.api.Assertions.assertThat;

class OrderServiceTest {

    @Test
    void should_be_a_right_adapter() {
        assertThat(OrderService.class).is(aRightAdapter());
    }

}
