package com.pse.coffee.domain;

import org.junit.jupiter.api.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aDrivenPort;
import static org.assertj.core.api.Assertions.assertThat;

class CoffeeStorageTest {

    @Test
    void should_be_a_driven_port() {
        assertThat(CoffeeStorage.class).is(aDrivenPort());
    }

}
