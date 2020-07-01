package com.pse.coffee.domain.catalogue;

public enum Unit {
    GRAM("g"), CL("cl");

    private Unit(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
