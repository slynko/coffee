package com.pse.coffee.domain.catalogue;

import com.pse.coffee.domain.DrinkName;
import com.pse.commons.architecture.HexagonalArchitecture;

import java.util.Optional;

import static com.pse.commons.architecture.HexagonalArchitecture.Port.Type.DRIVEN;

@HexagonalArchitecture.Port(DRIVEN)
public interface CatalogueSpi {
    Optional<CatalogueItem> find(DrinkName drink);
}
