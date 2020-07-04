package com.pse.coffee.domain.catalogue;

import org.junit.jupiter.api.Test;

import static com.pse.coffee.domain.catalogue.Unit.GRAM;
import static org.assertj.core.api.Assertions.assertThat;

class QuantityTest {

    @Test
    public void should_multiply() {
        assertThat(new Quantity(3, GRAM).times(2))
                .isEqualTo(new Quantity(6, GRAM));
    }

}
