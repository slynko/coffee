package com.pse.coffee.infra.driven;

import com.pse.coffee.domain.CoffeeStorage;
import com.pse.coffee.domain.DrinkName;
import com.pse.commons.HexagonalArchitecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@HexagonalArchitecture.RightAdapter
public class CoffeeService implements CoffeeStorage {
    private final static Logger LOG = LoggerFactory.getLogger(CoffeeService.class);

    public boolean isAvailable(DrinkName drinkName) {
        LOG.info(format("Right Adapter: Verify if this type of coffe available: %s", drinkName.name()));
        return true;
    }
}
