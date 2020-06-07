package com.pse.coffee.infra.driving;

import com.pse.coffee.domain.CommandHandler;
import com.pse.coffee.domain.Order;
import com.pse.commons.HexagonalArchitecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@HexagonalArchitecture.LeftAdapter
public class UserCommandHandler {
    private final static Logger LOG = LoggerFactory.getLogger(UserCommandHandler.class);

    private final CommandHandler userCommand;

    public UserCommandHandler(CommandHandler userCommand) {
        this.userCommand = userCommand;
    }

    public void handleUserCommand(final Order order) {
        // handle user command
        LOG.info(format("Left Adapter: Start user command handling: %s", order));
        userCommand.handleUserCommand(order);
    }
}
