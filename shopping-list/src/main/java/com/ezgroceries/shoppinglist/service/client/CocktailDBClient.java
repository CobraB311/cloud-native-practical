package com.ezgroceries.shoppinglist.service.client;

/*
    Created by Ruben Bernaert (JD68212) on 03/10/2019
*/

import com.ezgroceries.shoppinglist.service.CocktailService;
import com.ezgroceries.shoppinglist.service.client.model.CocktailDBResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1")
public interface CocktailDBClient extends CocktailService {

    @GetMapping(value = "search.php")
    @Override
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);

}
