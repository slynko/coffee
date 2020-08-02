package com.pse.coffee.infra.driven.catalogue;

import com.pse.coffee.domain.DrinkName;
import com.pse.coffee.domain.catalogue.Catalogue;
import com.pse.coffee.domain.catalogue.CatalogueItem;
import com.pse.commons.HexagonalArchitecture;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@HexagonalArchitecture.RightAdapter
public final class DefaultCatalogueAdapter implements Catalogue {

    @Override
    public Optional<CatalogueItem> find(@NonNull final DrinkName drink) {
        log.info("Right adapter: Find {} in catalogue", drink);
        return CatalogueEntry.of(drink)
                .map(CatalogueEntry::toCatalogueItem);
    }
}
