package com.pse.coffee;

import com.pse.coffee.domain.CommandHandler;
import com.pse.coffee.domain.model.CoffeeOrder;
import com.pse.coffee.domain.model.CoffeeType;
import com.pse.coffee.infra.driving.UserCommandHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SpringContextConfiguration.class})
public class AppTest {
    @Autowired
    private UserCommandHandler userCommandHandler;

    @Test
    public void appTest() {
        userCommandHandler.handleUserCommand(new CoffeeOrder(CoffeeType.LATTE));
    }
}
