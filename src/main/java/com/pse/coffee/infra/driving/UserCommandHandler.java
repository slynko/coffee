package com.pse.coffee.infra.driving;

import com.pse.coffee.domain.CoffeeShop;
import com.pse.coffee.domain.CommandHandler;
import com.pse.commons.HexagonalArchitecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pse.coffee.domain.CoffeeOrder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@HexagonalArchitecture.LeftAdapter
public class UserCommandHandler {
    private final static Logger LOG = LoggerFactory.getLogger(CoffeeShop.class);

    private final CommandHandler userCommand;

    public UserCommandHandler(CommandHandler userCommand) {
        this.userCommand = userCommand;
    }

    public void handleUserCommand(final CoffeeOrder coffeeOrder) {
        // handle user command
        LOG.info(format("Left Adapter: Start user command handling: %s", coffeeOrder));
        userCommand.handleUserCommand(coffeeOrder);
    }
}
