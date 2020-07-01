package com.pse.coffee.domain;

import lombok.*;

@Value
@AllArgsConstructor
public class PreparationDemand {
    @NonNull
    DrinkName drinkName;
    @NonNull
    int quantity;
    @NonNull
    String personName;
}
