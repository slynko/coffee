package com.pse.coffee.infra.driving.cucumber;

import com.pse.coffee.domain.CoffeeOrder;
import com.pse.coffee.domain.CoffeeShop;
import com.pse.coffee.domain.CoffeeType;
import com.pse.coffee.domain.CommandHandler;
import io.cucumber.java8.En;

import static org.assertj.core.api.Assertions.assertThat;

public final class OrderSteps implements En {

    private final World world;
    private final CommandHandler commandHandler;

    public OrderSteps(final World world,
                      final CoffeeShop commandHandler) {
        this.world = world;
        this.commandHandler = commandHandler;
        declareSteps();
    }

    private void declareSteps() {
        When("{word} orders {int} {word}", this::orderDrink);
        Then("he/she/they is/are fined {int}€", this::expectedAmountIsFined);
        Then("the order is rejected", this::expectedOrderToBeRejected);
    }

    private void orderDrink(final String customerName, final Integer quantity, final String drinkName) {
        final CoffeeType drink = CoffeeType.valueOf(drinkName.toUpperCase());
        final CoffeeOrder order = new CoffeeOrder(drink);
        boolean orderRejected;
        try {
            commandHandler.handleUserCommand(order);
            orderRejected = false;
            //TODO: world.price = result.getPrice()
        } catch (final Exception ex) { //TODO: use specific exception
            orderRejected = true;
        }
        world.orderRejected = orderRejected;
    }

    private void expectedAmountIsFined(final int amount) {
        assertThat(world.orderRejected).isFalse();
        //TODO: assertThat(world.requestedPrice).isEqualTo(amount);
    }

    private void expectedOrderToBeRejected() {
        assertThat(world.orderRejected).isTrue();
    }

}
