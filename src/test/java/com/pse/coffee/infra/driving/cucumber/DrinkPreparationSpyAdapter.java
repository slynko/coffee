package com.pse.coffee.infra.driving.cucumber;

import com.pse.coffee.domain.DrinkName;
import com.pse.coffee.domain.preparation.Drink;
import com.pse.coffee.domain.preparation.DrinkPreparation;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.lang.String.format;
import static lombok.AccessLevel.PRIVATE;
import static org.junit.jupiter.api.Assertions.fail;

public final class DrinkPreparationSpyAdapter implements DrinkPreparation {

    private final List<Drink> history = new ArrayList<>();

    @Override
    public void prepare(@NonNull final Drink drink) {
        history.add(drink);
    }

    public void isNeverCalled() {
        if (!history.isEmpty()) {
            fail("No interaction with drink preparation expected, but found " + history.size());
        }
    }

    public CallHistoryCheckWithNbCallsDefined isCalled(@NonNull final NumberOfCalls numberOfCalls) {
        return new CallHistoryCheck(numberOfCalls);
    }

    @Value
    @AllArgsConstructor(access = PRIVATE)
    public static class NumberOfCalls {
        long value;

        public static NumberOfCalls times(final int value) {
            return new NumberOfCalls(value);
        }
    }

    public interface CallHistoryCheckWithNbCallsDefined {
        CallHistoryCheckWithDrinkDefined forDrink(DrinkName drink);
    }

    public interface CallHistoryCheckWithDrinkDefined {
        void andCustomerName(String customerName);
    }

    private final class CallHistoryCheck implements CallHistoryCheckWithNbCallsDefined, CallHistoryCheckWithDrinkDefined {
        private final long expectedNumberOfCalls;
        private DrinkName drink;

        private CallHistoryCheck(final NumberOfCalls expectedNumberOfCalls) {
            this.expectedNumberOfCalls = expectedNumberOfCalls.getValue();
        }

        @Override
        public CallHistoryCheckWithDrinkDefined forDrink(@NonNull final DrinkName drink) {
            this.drink = drink;
            return this;
        }

        @Override
        public void andCustomerName(final String customerName) {
            final Stream<Drink> matchingCalls = history.stream()
                    .filter(drink -> drink.getName() == this.drink
                            && Objects.equals(drink.getPersonName(), customerName));
            if (matchingCalls.count() != expectedNumberOfCalls) {
                fail(format("%s interactions with drink preparation expected for { drink=%s, customerName=%s }, but found %s\nHistory:%s",
                        expectedNumberOfCalls, drink, customerName, history.size(), history));
            }
        }
    }
}
