package com.pse.coffee.infra;

import com.pse.coffee.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SpringContextConfiguration {

    @Bean
    public CustomerOrderHandler customerOrderHandler(final OrderPreparation orderHandler,
                                         final Stock stock, final Catalogue catalogue) {
        return new OrderingService(orderHandler, stock, catalogue);
    }
}
