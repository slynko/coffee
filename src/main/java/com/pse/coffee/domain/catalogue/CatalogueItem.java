package com.pse.coffee.domain.catalogue;

import com.pse.coffee.domain.DrinkName;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.joda.money.Money;

@Value
@AllArgsConstructor
public class CatalogueItem {
    DrinkName drink;
    Money price;
    Recipe recipe;
}
