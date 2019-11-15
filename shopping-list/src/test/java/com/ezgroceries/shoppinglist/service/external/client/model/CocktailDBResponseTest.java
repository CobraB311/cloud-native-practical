package com.ezgroceries.shoppinglist.service.external.client.model;

/*
    Created by Ruben Bernaert (JD68212) on 23/10/2019
*/

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CocktailDBResponseTest {

    @Test
    public void testSetIngredientsEmptyList() {
        CocktailDBResponse.DrinkResource resource = new CocktailDBResponse.DrinkResource();
        resource.setIngredients(new ArrayList<>());
        Assert.assertEquals(resource.getIngredients().size(), 0);
    }

    @Test
    public void testSetIngredients() {
        CocktailDBResponse.DrinkResource resource = new CocktailDBResponse.DrinkResource();
        resource.setIngredients(
                Lists.newArrayList(
                        "Banana", "Mango", "Kiwi"
                )
        );
        Assert.assertEquals(resource.getIngredients().size(), 3);
        Assert.assertTrue("Ingredients contain banana", resource.getIngredients().contains("Banana"));
        Assert.assertTrue("Ingredients contain mango", resource.getIngredients().contains("Mango"));
        Assert.assertTrue("Ingredients contain kiwi", resource.getIngredients().contains("Kiwi"));
    }

    @Test
    public void testSetIngredientsBigList() {
        CocktailDBResponse.DrinkResource resource = new CocktailDBResponse.DrinkResource();
        resource.setIngredients(
                Lists.newArrayList(
                        "Banana", "Mango", "Kiwi", "Apple", "Vodka", "Coke", "Magic", "Ice", "Lemon"
                )
        );
        Assert.assertEquals(resource.getIngredients().size(), 7);
    }

}
