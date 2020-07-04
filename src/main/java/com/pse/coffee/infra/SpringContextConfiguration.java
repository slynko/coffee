package com.pse.coffee.infra;

import com.pse.coffee.domain.*;
import com.pse.coffee.domain.catalogue.Catalogue;
import com.pse.coffee.domain.preparation.OrderPreparation;
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
