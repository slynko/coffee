package com.pse.coffee.infra.driven.catalogue;

import com.pse.coffee.domain.catalogue.Catalogue;
import com.pse.coffee.domain.DrinkName;
import com.pse.coffee.domain.catalogue.CatalogueItem;
import com.pse.coffee.domain.recipe.Quantity;
import com.pse.coffee.domain.recipe.Recipe;
import com.pse.commons.HexagonalArchitecture;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.pse.coffee.domain.DrinkName.ESPRESSO;
import static com.pse.coffee.domain.DrinkName.LATTE;
import static com.pse.coffee.domain.recipe.Ingredient.COFFEE_BEANS;
import static com.pse.coffee.domain.recipe.Ingredient.MILK;
import static com.pse.coffee.domain.recipe.Unit.CL;
import static com.pse.coffee.domain.recipe.Unit.GRAM;
import static org.joda.money.CurrencyUnit.EUR;

@Slf4j
@Service
@HexagonalArchitecture.RightAdapter
public final class DefaultCatalogueAdapter implements Catalogue {

    @Override
    public Optional<CatalogueItem> find(@NonNull final DrinkName drink) {
        log.info("Right adapter: Find {} in catalogue", drink);

        switch (drink) {
            case LATTE:
                return Optional.of(CatalogueItem.builder()
                        .drink(LATTE)
                        .unitCost(Money.of(EUR, 5))
                        .recipe(Recipe.builder()
                                .ingredient(COFFEE_BEANS, new Quantity(7, GRAM))
                                .ingredient(MILK, new Quantity(5, CL))
                                .build())
                        .build());
            case ESPRESSO:
                return Optional.of(CatalogueItem.builder()
                        .drink(ESPRESSO)
                        .unitCost(Money.of(EUR, 3))
                        .recipe(Recipe.builder()
                                .ingredient(COFFEE_BEANS, new Quantity(10, GRAM))
                                .build())
                        .build());
        }
        return Optional.empty();
    }
}
