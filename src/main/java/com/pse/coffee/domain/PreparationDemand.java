package com.pse.coffee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class PreparationDemand {
    @NonNull DrinkName drink;
    int quantity;
    @NonNull String personName;
}
