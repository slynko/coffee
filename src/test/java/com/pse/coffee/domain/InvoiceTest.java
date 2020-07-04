package com.pse.coffee.domain;

import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import static com.pse.coffee.domain.DrinkName.ESPRESSO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.EUR;

class InvoiceTest {

    @Test
    void should_compute_total_cost() {
        final Invoice invoice = Invoice.builder()
                .drink(ESPRESSO)
                .quantity(3)
                .unitCost(Money.of(EUR, 7))
                .build();

        assertThat(invoice.getTotalCost()).isEqualTo(Money.of(EUR, 21));
    }

}
