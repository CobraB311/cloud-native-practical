package com.ezgroceries.shoppinglist.service.external.client;

/*
    Created by Ruben Bernaert (JD68212) on 06/11/2019
*/

import com.ezgroceries.shoppinglist.persistence.entities.MealEntity;
import com.ezgroceries.shoppinglist.persistence.repositories.MealRepository;
import com.ezgroceries.shoppinglist.service.external.client.model.MealDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealDBClientFallback implements MealDBClient {

    private final MealRepository mealRepository;

    @Autowired
    public MealDBClientFallback(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @Override
    public MealDBResponse searchMeals(String search) {

        final List<MealEntity> mealEntities = mealRepository.findByNameContainingIgnoreCase(search);

        MealDBResponse mealDBResponse = new MealDBResponse();

        mealDBResponse.setMeals(
                mealEntities.stream().map(e -> {
                    MealDBResponse.MealResource meal = new MealDBResponse.MealResource();
                    meal.setIdMeal(e.getIdMeal());
                    meal.setStrMeal(e.getName());
                    meal.setStrCategory(e.getCategory());
                    meal.setStrArea(e.getArea());
                    meal.setStrInstructions(e.getInstructions());
                    meal.setStrMealThumb(e.getImageLink());
                    meal.setTags(e.getTags());
                    meal.setStrYoutube(e.getYoutubeLink());
                    meal.setIngredients(new ArrayList<>(e.getIngredients()));
                    return meal;
                }).collect(Collectors.toList())
        );

        return mealDBResponse;
    }

}
