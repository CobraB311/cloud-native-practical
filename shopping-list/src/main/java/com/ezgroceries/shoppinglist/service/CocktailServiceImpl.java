package com.ezgroceries.shoppinglist.service;

/*
    Created by Ruben Bernaert (JD68212) on 03/10/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.service.client.CocktailDBClient;
import com.ezgroceries.shoppinglist.service.client.model.CocktailDBResponse;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CocktailServiceImpl implements CocktailService {

    private final CocktailDBClient cocktailDBClient;

    @Autowired
    public CocktailServiceImpl(CocktailDBClient cocktailDBClient) {
        this.cocktailDBClient = cocktailDBClient;
    }

    @Override
    public List<CocktailResource> searchCocktails(String search) {
        final CocktailDBResponse response = this.cocktailDBClient.searchCocktails(search);

        if (response == null || response.getDrinks() == null || response.getDrinks().isEmpty()) {
            return new ArrayList<>();
        }

        return response.getDrinks().stream().map(d -> new CocktailResource(
                UUID.randomUUID(),
                d.getStrDrink(),
                d.getStrGlass(),
                d.getStrInstructions(),
                d.getStrDrinkThumb(),
                getIngredients(d)
        )).collect(Collectors.toList());
    }

    private List<String> getIngredients(CocktailDBResponse.DrinkResource resource) {

        return Stream.of(
                resource.getStrIngredient1(),
                resource.getStrIngredient2(),
                resource.getStrIngredient3(),
                resource.getStrIngredient4(),
                resource.getStrIngredient5(),
                resource.getStrIngredient6(),
                resource.getStrIngredient7()
        ).filter(
                i -> !Strings.isNullOrEmpty(i)
        ).collect(
                Collectors.toList()
        );

    }

}
