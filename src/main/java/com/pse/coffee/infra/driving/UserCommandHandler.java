package com.pse.coffee.infra.driving;

import com.pse.coffee.domain.CoffeeShop;
import com.pse.coffee.domain.CommandHandler;
import com.pse.coffee.domain.model.CoffeeOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

/**
 * Driving Port Adapter for user command handling.
 */
@Service
public class UserCommandHandler {
    private final static Logger LOG = LoggerFactory.getLogger(CoffeeShop.class);

    private final CommandHandler userCommand;

    public UserCommandHandler(CommandHandler userCommand) {
        this.userCommand = userCommand;
    }

    public void handleUserCommand(final CoffeeOrder coffeeOrder) {
        // handle user command
        LOG.info(format("Driving Port Adapter: Start user command handling: %s", coffeeOrder));
        userCommand.handleUserCommand(coffeeOrder);
    }
}
