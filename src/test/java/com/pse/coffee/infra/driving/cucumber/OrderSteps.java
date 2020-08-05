package com.pse.coffee.infra.driving.cucumber;

import com.pse.coffee.domain.*;
import com.pse.coffee.infra.driven.catalogue.DefaultCatalogueAdapter;
import io.cucumber.java8.En;
import org.joda.money.Money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.EUR;

public final class OrderSteps implements En {

    private final World world;
    private final CustomerOrderHandlerApi customerOrderHandler;

    public OrderSteps(final World world,
                      final DrinkPreparationSpyAdapter drinkPreparation,
                      final InMemoryStockAdapter stock,
                      final DefaultCatalogueAdapter catalogue) {
        this.world = world;
        this.customerOrderHandler = new OrderingService(drinkPreparation, stock, catalogue);
        declareSteps();
    }

    private void declareSteps() {
        When("{word} orders {int} {word}", this::orderDrink);
        Then("he/she/they is/are fined {int}€", this::expectedAmountIsFined);
        Then("the order is rejected", this::expectedOrderToBeRejected);
    }

    private void orderDrink(final String customerName, final Integer quantity, final String drinkName) {
        final Order order = Order.builder()
                .drink(DrinkName.valueOf(drinkName.toUpperCase()))
                .quantity(quantity)
                .personName(customerName)
                .build();
        boolean orderRejected;
        try {
            final Invoice invoice = customerOrderHandler.process(order);
            orderRejected = false;
            world.requestedPrice = invoice.getTotalCost();
        } catch (final UnavailableIngredientException | UnknownDrinkException ex) {
            orderRejected = true;
        }
        world.orderRejected = orderRejected;
    }

    private void expectedAmountIsFined(final int amount) {
        assertThat(world.orderRejected).isFalse();
        assertThat(world.requestedPrice).isEqualTo(Money.of(EUR, amount));
    }

    private void expectedOrderToBeRejected() {
        assertThat(world.orderRejected).isTrue();
    }

}
