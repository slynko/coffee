package com.pse.coffee.domain;

import org.junit.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aDrivenPort;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderHandlerTest {

    @Test
    public void should_be_a_driven_port() {
        assertThat(OrderHandler.class).is(aDrivenPort());
    }

}
