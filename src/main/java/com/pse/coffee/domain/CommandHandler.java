package com.pse.coffee.domain;

import com.pse.commons.HexagonalArchitecture;

import static com.pse.commons.HexagonalArchitecture.Port.Type.DRIVING;

@HexagonalArchitecture.Port(DRIVING)
public interface CommandHandler {

    /**
     * Handle incoming user command.
     */
    OrderResult handleUserCommand(final Order command);

}
