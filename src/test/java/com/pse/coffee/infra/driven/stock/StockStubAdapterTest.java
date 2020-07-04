package com.pse.coffee.infra.driven.stock;

import com.pse.coffee.domain.catalogue.Quantity;
import org.junit.jupiter.api.Test;

import static com.pse.coffee.domain.Ingredient.MILK;
import static com.pse.coffee.domain.catalogue.Unit.CL;
import static com.pse.commons.HexagonalArchitectureConditions.aRightAdapter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class StockStubAdapterTest {

    private final StockStubAdapter adapter = new StockStubAdapter();

    @Test
    void should_be_a_right_adapter() {
        assertThat(StockStubAdapter.class).is(aRightAdapter());
    }

    @Test
    void should_consider_that_ingredient_is_available() {
        assertThat(adapter.hasEnoughOf(MILK, new Quantity(100, CL))).isTrue();
    }

    @Test
    void should_fail_to_get_availability_for_null_ingredient() {
        assertThatNullPointerException()
                .isThrownBy(() -> adapter.hasEnoughOf(null, new Quantity(100, CL)));
    }

    @Test
    void should_fail_to_get_availability_for_null_required_quantity() {
        assertThatNullPointerException()
                .isThrownBy(() -> adapter.hasEnoughOf(MILK, null));
    }

}
