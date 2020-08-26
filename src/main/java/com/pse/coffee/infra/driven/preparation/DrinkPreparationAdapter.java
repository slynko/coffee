package com.pse.coffee.infra.driven.preparation;

import com.pse.coffee.domain.preparation.Drink;
import com.pse.coffee.domain.preparation.DrinkPreparationSpi;
import com.pse.commons.architecture.HexagonalArchitecture;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@HexagonalArchitecture.RightAdapter
public final class DrinkPreparationAdapter implements DrinkPreparationSpi {
    @Override
    public void prepare(@NonNull final Drink drink) {
        log.info("Right Adapter: Prepare drink: {}", drink);
    }
}
