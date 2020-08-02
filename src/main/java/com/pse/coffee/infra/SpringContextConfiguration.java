package com.pse.coffee.infra;

import com.pse.coffee.domain.CustomerOrderHandler;
import com.pse.coffee.domain.OrderingService;
import com.pse.coffee.domain.Stock;
import com.pse.coffee.domain.catalogue.Catalogue;
import com.pse.coffee.domain.preparation.DrinkPreparation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SpringContextConfiguration {

    @Bean
    public CustomerOrderHandler customerOrderHandler(final DrinkPreparation drinkPreparation,
                                                     final Stock stock, final Catalogue catalogue) {
        return new OrderingService(drinkPreparation, stock, catalogue);
    }
}
