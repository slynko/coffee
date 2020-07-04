package com.pse.coffee.infra.driving.order;

import com.pse.coffee.domain.CommandHandler;
import com.pse.coffee.domain.PreparationDemand;
import org.junit.jupiter.api.Test;

import static com.pse.coffee.domain.DrinkName.ESPRESSO;
import static com.pse.coffee.domain.OrderResult.OK;
import static com.pse.commons.HexagonalArchitectureConditions.aLeftAdapter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class UserCommandHandlerTest {

    private final CommandHandler service = mock(CommandHandler.class);
    private final UserCommandHandler adapter = new UserCommandHandler(service);

    @Test
    void should_be_a_left_adapter() {
        assertThat(UserCommandHandler.class).is(aLeftAdapter());
    }

    @Test
    void should_delegate_user_order_processing_to_domain() {
        final PreparationDemand order = PreparationDemand.builder()
                .personName("Vincent")
                .drink(ESPRESSO)
                .quantity(2)
                .build();
        given(service.handleUserCommand(order)).willReturn(OK);

        assertThat(adapter.handleUserCommand(order)).isEqualTo("OK");
    }

    @Test
    void should_fail_to_process_null_user_order() {
        assertThatNullPointerException()
                .isThrownBy(() -> adapter.handleUserCommand(null));
    }

}
