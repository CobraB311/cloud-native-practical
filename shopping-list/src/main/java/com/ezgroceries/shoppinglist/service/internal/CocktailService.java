package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CocktailService {

    List<CocktailResource> searchCocktails(@Nonnull String search);

    List<String> searchDistinctIngredients(Set<UUID> cocktailIds);

    List<CocktailResource> findCocktails(Set<UUID> cocktailsIds);

    List<CocktailEntity> createEntities(List<CocktailResource> cocktails);

}
