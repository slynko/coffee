package com.pse.coffee.domain;

public final class UnknownDrinkException extends RuntimeException {
    public UnknownDrinkException(final DrinkName drink) {
        super("No drink exists with name: " + drink);
    }
}
