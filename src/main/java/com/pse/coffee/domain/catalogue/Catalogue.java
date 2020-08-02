package com.pse.coffee.domain.catalogue;

import com.pse.coffee.domain.DrinkName;
import com.pse.commons.HexagonalArchitecture;

import java.util.Optional;

import static com.pse.commons.HexagonalArchitecture.Port.Type.DRIVEN;

@HexagonalArchitecture.Port(DRIVEN)
public interface Catalogue {
    Optional<CatalogueItem> find(DrinkName drink);
}
