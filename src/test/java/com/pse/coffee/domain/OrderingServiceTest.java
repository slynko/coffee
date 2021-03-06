package com.pse.coffee.domain;

import com.pse.coffee.domain.catalogue.CatalogueSpi;
import com.pse.coffee.domain.catalogue.CatalogueItem;
import com.pse.coffee.domain.preparation.Drink;
import com.pse.coffee.domain.preparation.DrinkPreparationSpi;
import com.pse.coffee.domain.recipe.Quantity;
import com.pse.coffee.domain.recipe.Recipe;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.pse.coffee.domain.DrinkName.LATTE;
import static com.pse.coffee.domain.recipe.Ingredient.COFFEE_BEANS;
import static com.pse.coffee.domain.recipe.Ingredient.MILK;
import static com.pse.coffee.domain.recipe.Unit.CL;
import static com.pse.coffee.domain.recipe.Unit.GRAM;
import static org.assertj.core.api.Assertions.*;
import static org.joda.money.CurrencyUnit.EUR;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class OrderingServiceTest {

    private final DrinkPreparationSpi preparation = mock(DrinkPreparationSpi.class);
    private final StockSpi stock = mock(StockSpi.class);
    private final CatalogueSpi catalogue = mock(CatalogueSpi.class);
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
        final Order order = Order.builder()
                .personName("Vincent")
                .drink(LATTE)
                .quantity(1)
                .build();

        assertThat(service.process(order)).isEqualTo(Invoice.builder()
                .drink(LATTE)
                .quantity(1)
                .unitCost(Money.of(EUR, 5))
                .build());
        then(preparation).should(times(1)).prepare(Drink.builder()
                .name(LATTE)
                .recipe(Recipe.builder()
                        .ingredient(COFFEE_BEANS, new Quantity(7, GRAM))
                        .ingredient(MILK, new Quantity(5, CL))
                        .build())
                .personName("Vincent")
                .build());
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
        final Order order = Order.builder()
                .personName("Vincent")
                .drink(LATTE)
                .quantity(2)
                .build();

        assertThat(service.process(order)).isEqualTo(Invoice.builder()
                .drink(LATTE)
                .quantity(2)
                .unitCost(Money.of(EUR, 5))
                .build());
        then(preparation).should(times(2)).prepare(Drink.builder()
                .name(LATTE)
                .recipe(Recipe.builder()
                        .ingredient(COFFEE_BEANS, new Quantity(7, GRAM))
                        .ingredient(MILK, new Quantity(5, CL))
                        .build())
                .personName("Vincent")
                .build());
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
        final Order order = Order.builder()
                .personName("Vincent")
                .drink(LATTE)
                .quantity(1)
                .build();

        assertThatExceptionOfType(UnavailableIngredientException.class)
                .isThrownBy(() -> service.process(order))
                .withMessage("Unavailable ingredient: %s", MILK);
        then(preparation).should(never()).prepare(any(Drink.class));
    }

    @Test
    void should_fail_to_process_order_if_drink_is_unknown() {
        given(catalogue.find(LATTE)).willReturn(Optional.empty());
        final Order order = Order.builder()
                .personName("Vincent")
                .drink(LATTE)
                .quantity(1)
                .build();

        assertThatExceptionOfType(UnknownDrinkException.class)
                .isThrownBy(() -> service.process(order))
                .withMessage("No drink exists with name: %s", LATTE);
        then(preparation).should(never()).prepare(any(Drink.class));
    }

    @Test
    void should_fail_to_process_null_order() {
        assertThatNullPointerException()
                .isThrownBy(() -> service.process(null));
    }

}
