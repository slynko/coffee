package com.pse.coffee.infra.driven.stock;

import com.pse.coffee.domain.Ingredient;
import com.pse.coffee.domain.Stock;
import com.pse.coffee.domain.recipe.Quantity;
import com.pse.commons.HexagonalArchitecture;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@HexagonalArchitecture.RightAdapter
public final class StockStubAdapter implements Stock {
    @Override
    public boolean hasEnoughOf(@NonNull final Ingredient ingredient, @NonNull final Quantity requiredQuantity) {
        log.info("Right adapter: Check stock for ingredient: {}, requiredQuantity: {}", ingredient, requiredQuantity);
        return true;
    }
}
