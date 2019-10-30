package com.ezgroceries.shoppinglist.service.external;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
*/

import com.ezgroceries.shoppinglist.model.MealResource;

import java.util.List;

public interface MealExtService {

    List<MealResource> searchMeals(String search);

}
