package com.pse.coffee;

import com.pse.coffee.infra.SpringContextConfiguration;
import com.pse.coffee.infra.driving.order.CustomerOrderController;
import com.pse.coffee.infra.driving.order.InvoiceDto;
import com.pse.coffee.infra.driving.order.OrderDto;
import org.joda.money.Money;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static com.pse.coffee.domain.DrinkName.LATTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.EUR;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SpringContextConfiguration.class})
class AppTest {
    @Autowired
    private CustomerOrderController userCommandHandler;

    @Test
    void appTest() {
        final OrderDto order = new OrderDto(LATTE, 3, "John");
        final Money expectedCost = Money.of(EUR, 15.);
        final InvoiceDto invoice = userCommandHandler.processOrder(order);

        assertThat(invoice.getTotalCost()).isEqualTo(expectedCost);
    }
}
