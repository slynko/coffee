package com.pse.coffee.domain.catalogue;

import lombok.NonNull;
import lombok.Value;

@Value
public class Quantity {
    int amount;
    @NonNull Unit unit;

    public Quantity times(final int factor) {
        return new Quantity(amount * factor, unit);
    }
}
