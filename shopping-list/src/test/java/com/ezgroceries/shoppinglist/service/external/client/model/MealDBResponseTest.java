package com.ezgroceries.shoppinglist.service.external.client.model;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
*/

import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

public class MealDBResponseTest {

    @Test
    public void testSetIngredientsEmptyList() {
        MealDBResponse.MealResource resource = new MealDBResponse.MealResource();
        resource.setIngredients(new ArrayList<>());
        Assert.assertEquals(resource.getIngredients().size(), 0);
    }

    @Test
    public void testSetIngredients() {
        MealDBResponse.MealResource resource = new MealDBResponse.MealResource();
        resource.setIngredients(
                Lists.newArrayList(
                        "Pork", "Curry", "Pepper"
                )
        );
        Assert.assertEquals(resource.getIngredients().size(), 3);
        Assert.assertTrue("Ingredients contain pork", resource.getIngredients().contains("Pork"));
        Assert.assertTrue("Ingredients contain curry", resource.getIngredients().contains("Curry"));
        Assert.assertTrue("Ingredients contain Pepper", resource.getIngredients().contains("Pepper"));
    }

    @Test
    public void testSetIngredientsBigList() {
        MealDBResponse.MealResource resource = new MealDBResponse.MealResource();
        resource.setIngredients(
                Lists.newArrayList(
                        "Pork", "Curry", "Pepper", "Apple", "Salmon", "Wine", "Magic", "Union", "Lemon", "penne", "olive", "oil", "garlic", "tomatoes", "basil",
                        "hamburger", "chicken", "salad", "peaches", "mushrooms"
                )
        );
        Assert.assertEquals(resource.getIngredients().size(), 20);
    }

    @Test
    public void testSetTagsEmptyList() {
        MealDBResponse.MealResource resource = new MealDBResponse.MealResource();
        resource.setTags(new HashSet<>());
        Assert.assertEquals(resource.getTags().size(), 0);
    }

    @Test
    public void testSetTags() {
        MealDBResponse.MealResource resource = new MealDBResponse.MealResource();
        resource.setTags(
                Sets.newHashSet(
                        "Car", "Plane", "Train"
                )
        );
        Assert.assertEquals(resource.getTags().size(), 3);
        Assert.assertTrue("Tags contain car", resource.getTags().contains("Car"));
        Assert.assertTrue("Tags contain plane", resource.getTags().contains("Plane"));
        Assert.assertTrue("Tags contain train", resource.getTags().contains("Train"));
    }

}
