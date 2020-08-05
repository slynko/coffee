package com.pse.coffee.domain.preparation;

import com.pse.commons.architecture.HexagonalArchitecture;

import static com.pse.commons.architecture.HexagonalArchitecture.Port.Type.DRIVEN;

@HexagonalArchitecture.Port(DRIVEN)
public interface DrinkPreparationSpi {
    void prepare(Drink drink);
}
