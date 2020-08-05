package com.pse.coffee.domain;

import com.pse.commons.HexagonalArchitecture;

import static com.pse.commons.HexagonalArchitecture.Port.Type.DRIVING;

@HexagonalArchitecture.Port(DRIVING)
public interface CustomerOrderHandlerApi {
    Invoice process(Order order);
}
