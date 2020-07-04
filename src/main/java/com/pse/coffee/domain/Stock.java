package com.pse.coffee.domain;

import com.pse.coffee.domain.catalogue.Quantity;
import com.pse.commons.HexagonalArchitecture;

import static com.pse.commons.HexagonalArchitecture.Port.Type.DRIVEN;

@HexagonalArchitecture.Port(DRIVEN)
public interface Stock {
    boolean hasEnoughOf(Ingredient ingredient, Quantity requiredQuantity);
}
