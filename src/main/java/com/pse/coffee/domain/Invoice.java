package com.pse.coffee.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.joda.money.Money;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class Invoice {

    @NonNull DrinkName drink;
    int quantity;
    @NonNull Money unitCost;

    public Money getTotalCost() {
        return unitCost.multipliedBy(quantity);
    }
}
