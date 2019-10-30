package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
*/

import com.ezgroceries.shoppinglist.model.MealResource;

import javax.annotation.Nonnull;
import java.util.List;

public interface MealService {

    List<MealResource> searchMeals(@Nonnull String search);

}
