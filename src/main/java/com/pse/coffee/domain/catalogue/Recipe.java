package com.pse.coffee.domain.catalogue;

import com.pse.coffee.domain.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Recipe {
    Map<Ingredient, Quantity> ingredients;
}
