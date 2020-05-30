package com.pse.coffee.infra.driving.cucumber;

import com.pse.coffee.domain.CoffeeType;
import com.pse.coffee.domain.OrderHandler;
import com.pse.coffee.infra.driven.OrderService;
import io.cucumber.java8.En;

public final class DrinkPreparationStep implements En {

    private final World world;
    private final OrderHandler orderHandler;

    public DrinkPreparationStep(final World world,
                                final OrderService orderHandler) {
        this.world = world;
        this.orderHandler = orderHandler;
        declareSteps();
    }

    private void declareSteps() {
        Then("{int} {word} is/are sent for preparation for {word}", this::expectDrinkToBeSentForPreparation);
        Then("no drink is sent for preparation", this::expectNoDrinkToBeSentForPreparation);
    }

    private void expectDrinkToBeSentForPreparation(final Integer quantity, final String drinkName, final String customerName) {
        final CoffeeType drink = CoffeeType.valueOf(drinkName.toUpperCase());
        //TODO: then(orderHandler).should(times(2)).sendDrinkForPreparation(drinkName, reference, ingredients)
    }

    private void expectNoDrinkToBeSentForPreparation() {
        //TODO: then(orderHandler).shouldNotHaveAnyInteraction()
    }

}
