package com.pse.coffee.domain.preparation;

import org.junit.jupiter.api.Test;

import static com.pse.commons.architecture.HexagonalArchitectureConditions.aDrivenPort;
import static org.assertj.core.api.Assertions.assertThat;

class DrinkPreparationSpiTest {

    @Test
    void should_be_a_driven_port() {
        assertThat(DrinkPreparationSpi.class).is(aDrivenPort());
    }

}
