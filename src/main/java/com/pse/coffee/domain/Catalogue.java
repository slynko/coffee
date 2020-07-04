package com.pse.coffee.domain;

import com.pse.coffee.domain.catalogue.CatalogueItem;
import com.pse.commons.HexagonalArchitecture;

import java.util.Optional;

import static com.pse.commons.HexagonalArchitecture.Port.Type.DRIVEN;

@HexagonalArchitecture.Port(DRIVEN)
public interface Catalogue {
    Optional<CatalogueItem> find(DrinkName drink);
}
