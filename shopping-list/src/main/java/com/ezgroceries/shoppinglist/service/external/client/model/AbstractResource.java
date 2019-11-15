package com.ezgroceries.shoppinglist.service.external.client.model;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
*/

import com.google.common.base.Strings;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public abstract class AbstractResource {

    private static final String STR_INGREDIENT = "strIngredient";

    public abstract Set<String> getIngredients();

    public abstract void setIngredients(List<String> ingredients);

    protected void setIngredients(List<String> ingredients, Class<? extends AbstractResource> clazz) {
        setIngredients(ingredients, 7, clazz);
    }

    protected void setIngredients(List<String> ingredients, int max, Class<? extends AbstractResource> clazz) {
        if (!ingredients.isEmpty()) {
            try {
                int iMax = Math.min(ingredients.size(), max);
                for (int i = 1; i <= iMax; i++) {
                    final String ingredient = ingredients.get(i - 1);
                    if (Strings.isNullOrEmpty(ingredient)) {
                        return; // Finish for loop because rest of ingredients will also be empty
                    }
                    Field field = clazz.getDeclaredField(STR_INGREDIENT + i);
                    field.setAccessible(true);
                    field.set(this, ingredient);
                }
            } catch (NoSuchFieldException e) {
                System.out.println("Error while converting ingredients: '" + e.getMessage() + "'.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
