package com.pse.coffee.infra.driven.preparation;

import org.junit.jupiter.api.Test;

import static com.pse.commons.HexagonalArchitectureConditions.aRightAdapter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class OrderPreparationAdapterTest {

    private final OrderPreparationAdapter adapter = new OrderPreparationAdapter();

    @Test
    void should_be_a_right_adapter() {
        assertThat(OrderPreparationAdapter.class).is(aRightAdapter());
    }

    @Test
    void should_fail_to_prepare_null_drink() {
        assertThatNullPointerException()
                .isThrownBy(() -> adapter.prepare(null));
    }

}
