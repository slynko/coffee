package com.pse.coffee.domain;

import org.junit.jupiter.api.Test;

import static com.pse.commons.architecture.HexagonalArchitectureConditions.aDrivingPort;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerOrderHandlerApiTest {

    @Test
    void should_be_a_driving_port() {
        assertThat(CustomerOrderHandlerApi.class).is(aDrivingPort());
    }

}
