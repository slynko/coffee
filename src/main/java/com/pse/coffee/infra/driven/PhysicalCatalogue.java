package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.Catalogue;
import com.pse.coffee.domain.DrinkName;
import com.pse.coffee.domain.catalogue.CatalogueItem;
import com.pse.coffee.domain.catalogue.Quantity;
import com.pse.coffee.domain.catalogue.Recipe;
import com.pse.commons.HexagonalArchitecture;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.stereotype.Service;

import static com.pse.coffee.domain.DrinkName.ESPRESSO;
import static com.pse.coffee.domain.DrinkName.LATTE;
import static com.pse.coffee.domain.Ingredient.COFFEE_BEANS;
import static com.pse.coffee.domain.Ingredient.MILK;
import static com.pse.coffee.domain.catalogue.Unit.CL;
import static com.pse.coffee.domain.catalogue.Unit.GRAM;
import static java.lang.String.format;
import static org.joda.money.CurrencyUnit.EUR;

@Slf4j
@Service
@HexagonalArchitecture.RightAdapter
public final class PhysicalCatalogue implements Catalogue {

    @Override
    public CatalogueItem getItemFor(@NonNull final DrinkName drink) {
        log.info("Right adapter: Get ingredients for: {}", drink);

        switch (drink) {
            case LATTE:
                return CatalogueItem.builder()
                        .drink(LATTE)
                        .unitCost(Money.of(EUR, 5))
                        .recipe(Recipe.builder()
                                .ingredient(COFFEE_BEANS, new Quantity(7, GRAM))
                                .ingredient(MILK, new Quantity(5, CL))
                                .build())
                        .build();
            case ESPRESSO:
                return CatalogueItem.builder()
                        .drink(ESPRESSO)
                        .unitCost(Money.of(EUR, 3))
                        .recipe(Recipe.builder()
                                .ingredient(COFFEE_BEANS, new Quantity(10, GRAM))
                                .build())
                        .build();
        }
        throw new IllegalArgumentException(format("No drink name found: %s", drink));
    }
}
