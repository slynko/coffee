package com.pse.coffee.domain;

/**
 * Driving Port for user commands handling.
 */
public interface CommandHandler {

    /**
     * Handle incoming user command.
     */
    void handleUserCommand(final CoffeeOrder command);

}
