package com.pse.coffee.domain;

import com.pse.commons.architecture.HexagonalArchitecture;

import static com.pse.commons.architecture.HexagonalArchitecture.Port.Type.DRIVING;

@HexagonalArchitecture.Port(DRIVING)
public interface CustomerOrderHandlerApi {
    Invoice process(Order order);
}
