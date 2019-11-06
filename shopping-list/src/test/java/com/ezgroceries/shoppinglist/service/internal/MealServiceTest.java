package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 06/11/2019
*/

import com.ezgroceries.shoppinglist.AbstractTest;
import com.ezgroceries.shoppinglist.model.MealResource;
import com.ezgroceries.shoppinglist.persistence.repositories.MealRepository;
import com.ezgroceries.shoppinglist.service.external.MealExtService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anySet;
import static org.mockito.Mockito.when;

public class MealServiceTest extends AbstractTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private MealExtService mealExtService;

    private MealService mealService;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);

        this.mealService = new MealServiceImpl(mealExtService, mealRepository);

        when(mealExtService.searchMeals("test")).thenReturn(mockedMeals());
        when(mealRepository.findAllById(anySet())).thenReturn(mockedShoppingListEntity().getMealEntities());
    }

    @Test
    public void searchMeals() {
        final List<MealResource> meals = mealService.searchMeals("test");
        checkMeals(meals);
    }

    private void checkMeals(List<MealResource> meals) {
        assertEquals(mockedMeals().size(), meals.size());

        assertEquals(meals.size(), 1);

        checkMeal(mockedMeals().get(0), meals.get(0));
    }

    private void checkMeal(MealResource expected, MealResource actual) {
        assertNotNull(actual.getMealId());
        assertEquals(expected.getMealId(), actual.getMealId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getCategory(), actual.getCategory());
        assertEquals(expected.getArea(), actual.getArea());
        assertEquals(expected.getInstructions(), actual.getInstructions());
        assertEquals(expected.getImage(), actual.getImage());
        assertEquals(expected.getYoutube(), actual.getYoutube());
        checkTags(expected.getTags(), actual.getTags());
        checkIngredients(expected.getIngredients(), actual.getIngredients());
    }

    private void checkTags(Set<String> expected, Set<String> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
    }

    private void checkIngredients(Set<String> expected, Set<String> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
    }

}
