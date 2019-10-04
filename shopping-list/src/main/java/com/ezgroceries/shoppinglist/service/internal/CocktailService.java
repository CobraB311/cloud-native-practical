package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.service.external.client.model.CocktailDBResponse;

import java.util.List;

public interface CocktailService {

    List<CocktailResource> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks);

}
