package com.ezgroceries.shoppinglist.service.external.client;

/*
    Created by Ruben Bernaert (JD68212) on 17/10/2019
*/

import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.service.external.client.model.CocktailDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CocktailDBClientFallback implements CocktailDBClient {

    private final CocktailRepository cocktailRepository;

    @Autowired
    public CocktailDBClientFallback(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    @Override
    public CocktailDBResponse searchCocktails(String search) {

        final List<CocktailEntity> cocktailEntities = cocktailRepository.findByNameContainingIgnoreCase(search);

        CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();

        cocktailDBResponse.setDrinks(
                cocktailEntities.stream().map(e -> {
                    CocktailDBResponse.DrinkResource drink = new CocktailDBResponse.DrinkResource();
                    drink.setIdDrink(e.getIdDrink());
                    drink.setStrDrink(e.getName());
                    drink.setStrGlass(e.getGlass());
                    drink.setStrInstructions(e.getInstructions());
                    drink.setStrDrinkThumb(e.getImageLink());
                    drink.setIngredients(new ArrayList<>(e.getIngredients()));
                    return drink;
                }).collect(Collectors.toList())
        );

        return cocktailDBResponse;
    }

}
