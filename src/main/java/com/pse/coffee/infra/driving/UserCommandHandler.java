package com.pse.coffee.infra.driving;

import com.pse.coffee.domain.CommandHandler;
import com.pse.coffee.domain.PreparationDemand;
import com.pse.commons.HexagonalArchitecture;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@HexagonalArchitecture.LeftAdapter
@Slf4j
@AllArgsConstructor
public final class UserCommandHandler {
    private final CommandHandler userCommand;

    public String handleUserCommand(@NonNull final PreparationDemand order) {
        log.info("Left Adapter: Start user command handling: {}", order);
        return userCommand.handleUserCommand(order).name();
    }
}
