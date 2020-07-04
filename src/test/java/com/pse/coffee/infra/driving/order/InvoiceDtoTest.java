package com.pse.coffee.infra.driving.order;

import com.pse.coffee.domain.Invoice;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;

import static com.pse.coffee.domain.DrinkName.ESPRESSO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.joda.money.CurrencyUnit.EUR;

class InvoiceDtoTest {

    @Test
    void should_map_from_domain() {
        final Invoice domainObject = Invoice.builder()
                .drink(ESPRESSO)
                .quantity(3)
                .unitCost(Money.of(EUR, 7))
                .build();

        assertThat(InvoiceDto.from(domainObject)).isEqualTo(new InvoiceDto(
                Money.of(EUR, 21)
        ));
    }

    @Test
    void should_fail_to_map_null_from_domain() {
        assertThatNullPointerException()
                .isThrownBy(() -> InvoiceDto.from(null));
    }
}
