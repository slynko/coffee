package com.pse.coffee.domain;

import com.pse.coffee.domain.catalogue.Catalogue;
import com.pse.coffee.domain.catalogue.CatalogueItem;
import com.pse.coffee.domain.preparation.Drink;
import com.pse.coffee.domain.preparation.OrderPreparation;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import static java.util.stream.IntStream.range;

@Slf4j
@AllArgsConstructor
public final class OrderingService implements CustomerOrderHandler {

    private final OrderPreparation preparation;
    private final Stock stock;
    private final Catalogue catalogue;

    public OrderResult process(@NonNull final Order order) {
        log.info("Domain: Start order processing: {}", order);

        final DrinkName drink = order.getDrink();
        final CatalogueItem catalogueItem = catalogue.find(drink)
                .orElseThrow(() -> new UnknownDrinkException(drink));
        final boolean ingredientsAreMissing = catalogueItem.getRecipe().getIngredients().entrySet().stream()
                .anyMatch(ingredient -> !stock.hasEnoughOf(ingredient.getKey(), ingredient.getValue().times(order.getQuantity())));

        if (ingredientsAreMissing) {
            return OrderResult.INGREDIENT_MISSING;
        }
        range(0, order.getQuantity()).forEach(any ->
                preparation.prepare(Drink.builder()
                        .name(order.getDrink())
                        .recipe(catalogueItem.getRecipe())
                        .personName(order.getPersonName())
                        .build())
        );
        log.info("Domain: Finish order processing: {}", order);
        return OrderResult.OK;
    }
}
