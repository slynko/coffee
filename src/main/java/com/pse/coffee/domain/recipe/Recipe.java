package com.pse.coffee.domain.recipe;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class Recipe {
    @Singular
    @NonNull Map<Ingredient, Quantity> ingredients;
}
