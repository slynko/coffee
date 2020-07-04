package com.pse.coffee.domain.catalogue;

import com.pse.coffee.domain.DrinkName;
import com.pse.coffee.domain.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.joda.money.Money;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class CatalogueItem {
    @NonNull DrinkName drink;
    @NonNull Money unitCost;
    @NonNull Recipe recipe;
}
