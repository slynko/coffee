package com.pse.coffee.domain.recipe;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toSet;
import static lombok.AccessLevel.PRIVATE;

@Value
@AllArgsConstructor(access = PRIVATE)
public class Recipe {
    @NonNull Collection<Recipe.Entry> entries;

    @Value
    public static class Entry {
        @NonNull Ingredient ingredient;
        @NonNull Quantity requiredQuantity;
    }

    public static RecipeBuilder builder() {
        return new RecipeBuilder();
    }

    public static class RecipeBuilder {
        private final Map<Ingredient, Quantity> ingredients = new HashMap<>();

        public RecipeBuilder ingredient(@NonNull final Ingredient ingredient, @NonNull final Quantity requiredQuantity) {
            if (ingredients.containsKey(ingredient)) {
                throw new IllegalStateException("Recipe already contains an entry for ingredient " + ingredient);
            }
            ingredients.put(ingredient, requiredQuantity);
            return this;
        }

        public Recipe build() {
            final Collection<Recipe.Entry> items = ingredients.entrySet().stream()
                    .map(entry -> new Recipe.Entry(entry.getKey(), entry.getValue()))
                    .collect(toSet());
            return new Recipe(items);
        }
    }
}
