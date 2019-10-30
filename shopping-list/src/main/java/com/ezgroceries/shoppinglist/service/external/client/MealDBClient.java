package com.ezgroceries.shoppinglist.service.external.client;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
*/

import com.ezgroceries.shoppinglist.service.external.client.model.MealDBResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "mealDBClient", url = "https://www.themealdb.com/api/json/v1/1")
public interface MealDBClient {

    @GetMapping(value = "search.php")
    MealDBResponse searchMeals(@RequestParam("s") String search);

}
