package com.pse.coffee.domain;

import com.pse.coffee.domain.model.CoffeeOrder;

/**
 * Driving Port for user commands handling.
 */
public interface CommandHandler {

    /**
     * Handle incoming user command.
     */
    void handleUserCommand(final CoffeeOrder command);

}
