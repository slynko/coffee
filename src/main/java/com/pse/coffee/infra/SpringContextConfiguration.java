package com.pse.coffee.infra;

import com.pse.coffee.domain.CustomerOrderHandlerApi;
import com.pse.coffee.domain.OrderingService;
import com.pse.coffee.domain.StockSpi;
import com.pse.coffee.domain.catalogue.CatalogueSpi;
import com.pse.coffee.domain.preparation.DrinkPreparationSpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SpringContextConfiguration {

    @Bean
    public CustomerOrderHandlerApi customerOrderHandler(final DrinkPreparationSpi drinkPreparation,
                                                        final StockSpi stock, final CatalogueSpi catalogue) {
        return new OrderingService(drinkPreparation, stock, catalogue);
    }
}
