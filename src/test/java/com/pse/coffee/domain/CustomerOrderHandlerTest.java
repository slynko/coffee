package com.pse.coffee.domain;

import org.junit.jupiter.api.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aDrivingPort;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerOrderHandlerTest {

    @Test
    void should_be_a_driving_port() {
        assertThat(CustomerOrderHandler.class).is(aDrivingPort());
    }

}