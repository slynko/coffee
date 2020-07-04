package com.pse.coffee.infra.driven.preparation;

import com.pse.coffee.domain.preparation.Drink;
import com.pse.coffee.domain.preparation.OrderPreparation;
import com.pse.commons.HexagonalArchitecture;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@HexagonalArchitecture.RightAdapter
public final class OrderPreparationAdapter implements OrderPreparation {
    @Override
    public void prepare(@NonNull final Drink drink) {
        log.info("Right Adapter: Prepare drink: {}", drink);
    }
}
