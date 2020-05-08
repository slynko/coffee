package com.pse.coffee.domain;

import org.junit.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aDrivenPort;
import static org.assertj.core.api.Assertions.assertThat;

public class CoffeeStorageTest {

    @Test
    public void should_be_a_driven_port() {
        assertThat(CoffeeStorage.class).is(aDrivenPort());
    }

}
