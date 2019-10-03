package com.ezgroceries.shoppinglist.service;

/*
    Created by Ruben Bernaert (JD68212) on 03/10/2019
*/

import com.ezgroceries.shoppinglist.service.client.model.CocktailDBResponse;

public interface CocktailService {

    CocktailDBResponse searchCocktails(String search);

}
