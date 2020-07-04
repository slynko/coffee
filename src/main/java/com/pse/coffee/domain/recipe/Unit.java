package com.pse.coffee.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Unit {
    GRAM("g"),
    CL("cl");

    private final String name;
}
