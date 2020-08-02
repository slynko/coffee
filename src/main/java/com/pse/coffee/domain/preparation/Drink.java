package com.pse.coffee.domain.preparation;

import com.pse.coffee.domain.DrinkName;
import com.pse.coffee.domain.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import static lombok.AccessLevel.PRIVATE;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
public class Drink {
    @NonNull DrinkName name;
    @NonNull Recipe recipe;
    @NonNull String personName;
}
