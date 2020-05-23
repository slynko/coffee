package com.pse.coffee.domain;

public class Order {
    private DrinkName drinkName;

    public Order(DrinkName drinkName) {
        this.drinkName = drinkName;
    }

    public DrinkName getDrinkName() {
        return drinkName;
    }

    public void setCoffeeType(DrinkName drinkName) {
        this.drinkName = drinkName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "drinkName=" + drinkName +
                '}';
    }
}
