package com.pse.coffee.infra.driving;

import com.pse.coffee.domain.UserCommand;

/**
 * Driving Port Adapter for user command handling.
 */
public class UserCommandHandler implements UserCommand {
    private UserCommand userCommand;

    public void handleUserCommand(final Object command) {
        // handle user command
        userCommand.handleUserCommand(command);
    }
}
