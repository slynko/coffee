package com.pse.coffee.domain;

import com.pse.coffee.domain.catalogue.CatalogueItem;
import com.pse.coffee.domain.catalogue.Quantity;
import com.pse.coffee.domain.catalogue.Recipe;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.pse.coffee.domain.DrinkName.LATTE;
import static com.pse.coffee.domain.Ingredient.COFFEE_BEANS;
import static com.pse.coffee.domain.Ingredient.MILK;
import static com.pse.coffee.domain.OrderResult.INGREDIENT_MISSING;
import static com.pse.coffee.domain.OrderResult.OK;
import static com.pse.coffee.domain.catalogue.Unit.CL;
import static com.pse.coffee.domain.catalogue.Unit.GRAM;
import static org.assertj.core.api.Assertions.*;
import static org.joda.money.CurrencyUnit.EUR;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class OrderingServiceTest {

    private final OrderPreparation preparation = mock(OrderPreparation.class);
    private final Stock stock = mock(Stock.class);
    private final Catalogue catalogue = mock(Catalogue.class);
    private final OrderingService service = new OrderingService(preparation, stock, catalogue);

    @Test
    void should_process_order() {
        given(catalogue.find(LATTE)).willReturn(Optional.of(CatalogueItem.builder()
                .drink(LATTE)
                .unitCost(Money.of(EUR, 5))
                .recipe(Recipe.builder()
                        .ingredient(COFFEE_BEANS, new Quantity(7, GRAM))
                        .ingredient(MILK, new Quantity(5, CL))
                        .build())
                .build()));
        given(stock.hasEnoughOf(COFFEE_BEANS, new Quantity(7, GRAM))).willReturn(true);
        given(stock.hasEnoughOf(MILK, new Quantity(5, CL))).willReturn(true);
        final PreparationDemand order = PreparationDemand.builder()
                .personName("Vincent")
                .drink(LATTE)
                .quantity(1)
                .build();

        assertThat(service.process(order)).isEqualTo(OK);
    }

    @Test
    void should_process_order_with_quantity_greater_than_1() {
        given(catalogue.find(LATTE)).willReturn(Optional.of(CatalogueItem.builder()
                .drink(LATTE)
                .unitCost(Money.of(EUR, 5))
                .recipe(Recipe.builder()
                        .ingredient(COFFEE_BEANS, new Quantity(7, GRAM))
                        .ingredient(MILK, new Quantity(5, CL))
                        .build())
                .build()));
        given(stock.hasEnoughOf(COFFEE_BEANS, new Quantity(14, GRAM))).willReturn(true);
        given(stock.hasEnoughOf(MILK, new Quantity(10, CL))).willReturn(true);
        final PreparationDemand order = PreparationDemand.builder()
                .personName("Vincent")
                .drink(LATTE)
                .quantity(2)
                .build();

        assertThat(service.process(order)).isEqualTo(OK);
    }

    @Test
    void should_fail_to_process_order_if_ingredients_are_missing() {
        given(catalogue.find(LATTE)).willReturn(Optional.of(CatalogueItem.builder()
                .drink(LATTE)
                .unitCost(Money.of(EUR, 5))
                .recipe(Recipe.builder()
                        .ingredient(COFFEE_BEANS, new Quantity(7, GRAM))
                        .ingredient(MILK, new Quantity(5, CL))
                        .build())
                .build()));
        given(stock.hasEnoughOf(COFFEE_BEANS, new Quantity(7, GRAM))).willReturn(true);
        given(stock.hasEnoughOf(MILK, new Quantity(5, CL))).willReturn(false);
        final PreparationDemand order = PreparationDemand.builder()
                .personName("Vincent")
                .drink(LATTE)
                .quantity(1)
                .build();

        assertThat(service.process(order)).isEqualTo(INGREDIENT_MISSING);
    }

    @Test
    void should_fail_to_process_order_if_drink_is_unknown() {
        given(catalogue.find(LATTE)).willReturn(Optional.empty());
        final PreparationDemand order = PreparationDemand.builder()
                .personName("Vincent")
                .drink(LATTE)
                .quantity(1)
                .build();

        assertThatExceptionOfType(UnknownDrinkException.class)
                .isThrownBy(() -> service.process(order))
                .withMessage("No drink exists with name: %s", LATTE);
    }

    @Test
    void should_fail_to_process_null_order() {
        assertThatNullPointerException()
                .isThrownBy(() -> service.process(null));
    }

}
