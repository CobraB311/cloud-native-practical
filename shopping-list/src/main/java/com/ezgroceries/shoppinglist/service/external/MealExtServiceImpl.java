package com.ezgroceries.shoppinglist.service.external;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
*/

import com.ezgroceries.shoppinglist.model.MealResource;
import com.ezgroceries.shoppinglist.service.external.client.MealDBClient;
import com.ezgroceries.shoppinglist.service.external.client.model.MealDBResponse;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MealExtServiceImpl implements MealExtService {

    private final MealDBClient mealDBClient;

    @Autowired
    public MealExtServiceImpl(MealDBClient mealDBClient) {
        this.mealDBClient = mealDBClient;
    }

    @Override
    public List<MealResource> searchMeals(String search) {
        final MealDBResponse response = this.mealDBClient.searchMeals(search);

        if (response == null || response.getMeals() == null || response.getMeals().isEmpty()) {
            return new ArrayList<>();
        }

        return response.getMeals().stream().map(m -> {

            final String tags = m.getStrTags();
            Set<String> tagSet = new HashSet<>();
            if (!Strings.isNullOrEmpty(tags)) {
                tagSet.addAll(Lists.newArrayList(tags.split(",")));
            }

            return new MealResource(
                    UUID.randomUUID(),
                    m.getIdMeal(),
                    m.getStrMeal(),
                    m.getStrCategory(),
                    m.getStrArea(),
                    m.getStrInstructions(),
                    m.getStrMealThumb(),
                    m.getStrYoutube(),
                    tagSet,
                    m.getIngredients()
            );
        }).collect(Collectors.toList());
    }

}
