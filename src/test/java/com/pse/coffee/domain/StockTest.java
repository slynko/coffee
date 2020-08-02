package com.pse.coffee.domain;

import org.junit.jupiter.api.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aDrivenPort;
import static org.assertj.core.api.Assertions.assertThat;

class StockTest {

    @Test
    void should_be_a_driven_port() {
        assertThat(Stock.class).is(aDrivenPort());
    }

}
