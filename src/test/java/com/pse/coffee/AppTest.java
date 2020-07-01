package com.pse.coffee;

import com.pse.coffee.domain.PreparationDemand;
import com.pse.coffee.domain.DrinkName;
import com.pse.coffee.infra.driving.UserCommandHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static com.pse.coffee.domain.DrinkName.LATTE;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {SpringContextConfiguration.class})
class AppTest {
    @Autowired
    private UserCommandHandler userCommandHandler;

    @Test
    void appTest() {
        userCommandHandler.handleUserCommand(new PreparationDemand(LATTE, 1, "John"));
    }
}
