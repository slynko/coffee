package com.pse.coffee.domain;

import com.pse.coffee.domain.catalogue.CatalogueItem;
import com.pse.commons.HexagonalArchitecture;

import static com.pse.commons.HexagonalArchitecture.Port.Type.DRIVEN;

@HexagonalArchitecture.Port(DRIVEN)
public interface Catalogue {

    CatalogueItem getItemFor(final DrinkName drink);

}
