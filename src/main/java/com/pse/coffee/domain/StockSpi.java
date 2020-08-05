package com.pse.coffee.domain;

import com.pse.coffee.domain.recipe.Ingredient;
import com.pse.coffee.domain.recipe.Quantity;
import com.pse.commons.architecture.HexagonalArchitecture;

import static com.pse.commons.architecture.HexagonalArchitecture.Port.Type.DRIVEN;

@HexagonalArchitecture.Port(DRIVEN)
public interface StockSpi {
    boolean hasEnoughOf(Ingredient ingredient, Quantity requiredQuantity);
}
