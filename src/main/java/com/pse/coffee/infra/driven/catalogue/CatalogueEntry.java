package com.pse.coffee.infra.driven.catalogue;

import com.pse.coffee.domain.DrinkName;
import com.pse.coffee.domain.catalogue.CatalogueItem;
import com.pse.coffee.domain.recipe.Quantity;
import com.pse.coffee.domain.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.joda.money.Money;

import java.util.Optional;

import static com.pse.coffee.domain.recipe.Ingredient.COFFEE_BEANS;
import static com.pse.coffee.domain.recipe.Ingredient.MILK;
import static com.pse.coffee.domain.recipe.Unit.CL;
import static com.pse.coffee.domain.recipe.Unit.GRAM;
import static java.util.Arrays.stream;
import static org.joda.money.CurrencyUnit.EUR;

@AllArgsConstructor
enum CatalogueEntry {
    LATTE(
            DrinkName.LATTE,
            Money.of(EUR, 5),
            Recipe.builder()
                    .ingredient(COFFEE_BEANS, new Quantity(7, GRAM))
                    .ingredient(MILK, new Quantity(5, CL))
                    .build()
    ),
    ESPRESSO(
            DrinkName.ESPRESSO,
            Money.of(EUR, 3),
            Recipe.builder()
                    .ingredient(COFFEE_BEANS, new Quantity(10, GRAM))
                    .build()
    ),
    ;

    private final DrinkName drink;
    private final Money unitCost;
    private final Recipe recipe;

    static Optional<CatalogueEntry> of(@NonNull final DrinkName drink) {
        return stream(CatalogueEntry.values())
                .filter(instance -> instance.drink == drink)
                .findAny();
    }

    CatalogueItem toCatalogueItem() {
        return CatalogueItem.builder()
                .drink(drink)
                .unitCost(unitCost)
                .recipe(recipe)
                .build();
    }
}
