package com.pse.coffee;

import com.pse.coffee.domain.*;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
public class SpringContextConfiguration {

    @Bean
    public CommandHandler commandHandler(final OrderPreparation orderHandler, final Stock stock, final Catalogue catalogue) {
        return new Ordering(orderHandler, stock, catalogue);
    }
}
