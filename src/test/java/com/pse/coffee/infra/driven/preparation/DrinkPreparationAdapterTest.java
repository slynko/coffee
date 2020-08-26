package com.pse.coffee.infra.driven.preparation;

import org.junit.jupiter.api.Test;

import static com.pse.commons.architecture.HexagonalArchitectureConditions.aRightAdapter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class DrinkPreparationAdapterTest {

    private final DrinkPreparationAdapter adapter = new DrinkPreparationAdapter();

    @Test
    void should_be_a_right_adapter() {
        assertThat(DrinkPreparationAdapter.class).is(aRightAdapter());
    }

    @Test
    void should_fail_to_prepare_null_drink() {
        assertThatNullPointerException()
                .isThrownBy(() -> adapter.prepare(null));
    }

}
