package com.pse.coffee.domain.recipe;

import lombok.NonNull;
import lombok.Value;

@Value
public class Quantity {
    int amount;
    @NonNull Unit unit;

    public Quantity multipliedBy(final int factor) {
        return new Quantity(amount * factor, unit);
    }
}
