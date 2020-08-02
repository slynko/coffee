package com.pse.coffee.infra.driving.order;

import com.pse.coffee.domain.Order;
import org.junit.jupiter.api.Test;

import static com.pse.coffee.domain.DrinkName.LATTE;
import static org.assertj.core.api.Assertions.assertThat;

class OrderDtoTest {

    @Test
    void should_map_to_domain() {
        final OrderDto dto = new OrderDto(LATTE, 1, "John");

        assertThat(dto.toDomain()).isEqualTo(Order.builder()
                .drink(LATTE)
                .quantity(1)
                .personName("John")
                .build());
    }
}
