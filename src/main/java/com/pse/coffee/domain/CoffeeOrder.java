package com.pse.coffee.domain;

public class CoffeeOrder {
    private CoffeeType coffeeType;

    public CoffeeOrder(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(CoffeeType coffeeType) {
        this.coffeeType = coffeeType;
    }

    @Override
    public String toString() {
        return "CoffeeOrder{" +
                "coffeeType=" + coffeeType +
                '}';
    }
}
