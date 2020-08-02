package com.pse.coffee.infra.driving.order;

import com.pse.coffee.domain.DrinkName;
import com.pse.coffee.domain.Order;
import lombok.NonNull;
import lombok.Value;

@Value
public class OrderDto {
    @NonNull DrinkName drink;
    int quantity;
    @NonNull String personName;

    public Order toDomain() {
        return Order.builder()
                .drink(drink)
                .quantity(quantity)
                .personName(personName)
                .build();
    }
}
