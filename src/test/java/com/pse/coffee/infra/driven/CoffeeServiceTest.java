package com.pse.coffee.infra.driven;

import org.junit.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aRightAdapter;
import static org.assertj.core.api.Assertions.assertThat;

public class CoffeeServiceTest {

    @Test
    public void should_be_a_right_adapter() {
        assertThat(CoffeeService.class).is(aRightAdapter());
    }

}
