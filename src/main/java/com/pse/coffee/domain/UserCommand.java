package com.pse.coffee.domain;

/**
 * Driving Port for user commands handling.
 */
public interface UserCommand {

    /**
     * Handle incoming user command.
     */
    void handleUserCommand(final Object command);

}
