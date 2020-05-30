package com.pse.coffee.infra.driving;

import org.junit.jupiter.api.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aLeftAdapter;
import static org.assertj.core.api.Assertions.assertThat;

class UserCommandHandlerTest {

    @Test
    void should_be_a_left_adapter() {
        assertThat(UserCommandHandler.class).is(aLeftAdapter());
    }

}
