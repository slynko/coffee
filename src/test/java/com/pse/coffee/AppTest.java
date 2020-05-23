package com.pse.coffee;

import com.pse.coffee.domain.model.CoffeeOrder;
import com.pse.coffee.domain.model.CoffeeType;
import com.pse.coffee.infra.driving.UserCommandHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SpringContextConfiguration.class})
class AppTest {
    @Autowired
    private UserCommandHandler userCommandHandler;

    @Test
    void appTest() {
        userCommandHandler.handleUserCommand(new CoffeeOrder(CoffeeType.LATTE));
    }
}
