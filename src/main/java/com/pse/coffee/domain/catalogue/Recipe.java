package com.pse.coffee.domain.catalogue;

import com.pse.coffee.domain.Ingredient;
import lombok.*;

import java.util.Map;

@Value
@Builder
public class Recipe {
    @NonNull
    @Singular
    Map<Ingredient, Quantity> ingredients;
}
