package com.pse.coffee.domain.preparation;

import com.pse.commons.HexagonalArchitecture;

import static com.pse.commons.HexagonalArchitecture.Port.Type.DRIVEN;

@HexagonalArchitecture.Port(DRIVEN)
public interface OrderPreparation {
    void prepare(Drink drink);
}
