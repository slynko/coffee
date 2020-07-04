package com.pse.coffee.domain.preparation;

import org.junit.jupiter.api.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aDrivenPort;
import static org.assertj.core.api.Assertions.assertThat;

class OrderPreparationTest {

    @Test
    void should_be_a_driven_port() {
        assertThat(OrderPreparation.class).is(aDrivenPort());
    }

}