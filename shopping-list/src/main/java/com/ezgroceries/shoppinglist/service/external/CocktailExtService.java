package com.ezgroceries.shoppinglist.service.external;

/*
    Created by Ruben Bernaert (JD68212) on 03/10/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;

import java.util.List;

public interface CocktailExtService {

    List<CocktailResource> searchCocktails(String search);

}
