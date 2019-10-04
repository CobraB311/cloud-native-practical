package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;

import javax.annotation.Nonnull;
import java.util.List;

public interface CocktailService {

    List<CocktailResource> searchCocktails(@Nonnull String search);

}
