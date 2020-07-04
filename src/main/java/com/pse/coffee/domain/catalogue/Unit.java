package com.pse.coffee.domain.catalogue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Unit {
    GRAM("g"),
    CL("cl");

    private final String name;
}
