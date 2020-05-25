package com.pse.coffee.infra.driving;

import org.junit.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aLeftAdapter;
import static org.assertj.core.api.Assertions.assertThat;

public class UserCommandHandlerTest {

    @Test
    public void should_be_a_left_adapter() {
        assertThat(UserCommandHandler.class).is(aLeftAdapter());
    }

}
