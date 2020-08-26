package com.pse.coffee.domain;

import org.junit.jupiter.api.Test;

import static com.pse.commons.architecture.HexagonalArchitectureConditions.aDrivenPort;
import static org.assertj.core.api.Assertions.assertThat;

class StockSpiTest {

    @Test
    void should_be_a_driven_port() {
        assertThat(StockSpi.class).is(aDrivenPort());
    }

}
