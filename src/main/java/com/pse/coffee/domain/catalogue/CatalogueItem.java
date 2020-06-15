package com.pse.coffee.domain.catalogue;

import com.pse.coffee.domain.DrinkName;
import lombok.Data;

@Data
public class CatalogueItem {
    private final DrinkName drinkName;
    private final int price; //todo choose another type ?
    private final Recipe recipe;
}
