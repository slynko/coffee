package com.pse.coffee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class Recipe {
    Set<IngredientMeasurable> ingredients;
}
