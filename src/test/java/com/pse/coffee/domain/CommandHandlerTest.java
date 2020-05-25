package com.pse.coffee.domain;

import org.junit.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aDrivingPort;
import static org.assertj.core.api.Assertions.assertThat;

public class CommandHandlerTest {

    @Test
    public void should_be_a_driving_port() {
        assertThat(CommandHandler.class).is(aDrivingPort());
    }

}
