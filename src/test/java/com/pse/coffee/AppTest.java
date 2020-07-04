package com.pse.coffee;

import com.pse.coffee.domain.Order;
import com.pse.coffee.infra.SpringContextConfiguration;
import com.pse.coffee.infra.driving.order.CustomerOrderController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static com.pse.coffee.domain.DrinkName.LATTE;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SpringContextConfiguration.class})
class AppTest {
    @Autowired
    private CustomerOrderController userCommandHandler;

    @Test
    void appTest() {
        final String result = userCommandHandler.processOrder(Order.builder()
                .drink(LATTE)
                .quantity(1)
                .personName("John")
                .build());

        assertThat(result).isEqualTo("OK");
    }
}
