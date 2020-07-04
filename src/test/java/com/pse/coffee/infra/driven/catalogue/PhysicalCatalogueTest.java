package com.pse.coffee.infra.driven.catalogue;

import com.pse.coffee.domain.catalogue.CatalogueItem;
import com.pse.coffee.domain.catalogue.Quantity;
import com.pse.coffee.domain.catalogue.Recipe;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import static com.pse.coffee.domain.DrinkName.ESPRESSO;
import static com.pse.coffee.domain.DrinkName.LATTE;
import static com.pse.coffee.domain.Ingredient.COFFEE_BEANS;
import static com.pse.coffee.domain.Ingredient.MILK;
import static com.pse.coffee.domain.catalogue.Unit.CL;
import static com.pse.coffee.domain.catalogue.Unit.GRAM;
import static com.pse.commons.HexagonalArchitectureConditions.aRightAdapter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.joda.money.CurrencyUnit.EUR;

class PhysicalCatalogueTest {

    private final PhysicalCatalogue adapter = new PhysicalCatalogue();

    @Test
    void should_be_a_right_adapter() {
        assertThat(PhysicalCatalogue.class).is(aRightAdapter());
    }

    @Test
    void should_get_item_for_LATTE() {
        assertThat(adapter.getItemFor(LATTE)).isEqualTo(CatalogueItem.builder()
                .drink(LATTE)
                .unitCost(Money.of(EUR, 5))
                .recipe(Recipe.builder()
                        .ingredient(COFFEE_BEANS, new Quantity(7, GRAM))
                        .ingredient(MILK, new Quantity(5, CL))
                        .build())
                .build());
    }

    @Test
    void should_get_item_for_ESPRESSO() {
        assertThat(adapter.getItemFor(ESPRESSO)).isEqualTo(CatalogueItem.builder()
                .drink(ESPRESSO)
                .unitCost(Money.of(EUR, 3))
                .recipe(Recipe.builder()
                        .ingredient(COFFEE_BEANS, new Quantity(10, GRAM))
                        .build())
                .build());
    }

    @Test
    void should_fail_to_get_item_for_null_drink() {
        assertThatNullPointerException()
                .isThrownBy(() -> adapter.getItemFor(null));
    }

}
