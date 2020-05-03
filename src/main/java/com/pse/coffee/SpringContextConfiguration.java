package com.pse.coffee;

import com.pse.coffee.domain.*;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
public class SpringContextConfiguration {

    @Bean
    public CommandHandler commandHandler(final OrderHandler orderHandler, final CoffeeStorage coffeeStorage) {
        return new CoffeeShop(orderHandler, coffeeStorage);
    }
}
