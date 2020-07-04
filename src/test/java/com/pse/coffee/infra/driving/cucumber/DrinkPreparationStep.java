package com.pse.coffee.infra.driving.cucumber;

import com.pse.coffee.domain.DrinkName;
import io.cucumber.java8.En;

import static com.pse.coffee.infra.driving.cucumber.DrinkPreparationSpyAdapter.NumberOfCalls.times;

public final class DrinkPreparationStep implements En {

    private final DrinkPreparationSpyAdapter drinkPreparation;

    public DrinkPreparationStep(final DrinkPreparationSpyAdapter drinkPreparation) {
        this.drinkPreparation = drinkPreparation;
        declareSteps();
    }

    private void declareSteps() {
        Then("{int} {word} is/are sent for preparation for {word}", this::expectDrinkToBeSentForPreparation);
        Then("no drink is sent for preparation", this::expectNoDrinkToBeSentForPreparation);
    }

    private void expectDrinkToBeSentForPreparation(final Integer quantity, final String drinkName, final String customerName) {
        final DrinkName drink = DrinkName.valueOf(drinkName.toUpperCase());
        drinkPreparation.isCalled(times(quantity))
                .forDrink(drink)
                .andCustomerName(customerName);
    }

    private void expectNoDrinkToBeSentForPreparation() {
        drinkPreparation.isNeverCalled();
    }

}
