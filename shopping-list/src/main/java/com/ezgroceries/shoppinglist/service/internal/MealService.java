package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
*/

import com.ezgroceries.shoppinglist.model.MealResource;
import com.ezgroceries.shoppinglist.persistence.entities.MealEntity;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface MealService {

    List<MealResource> searchMeals(@Nonnull String search);

    List<String> searchDistinctIngredients(Set<UUID> mealIds);

    List<MealResource> findMeals(Set<UUID> mealIds);

    List<MealEntity> createEntities(List<MealResource> meals);

}
