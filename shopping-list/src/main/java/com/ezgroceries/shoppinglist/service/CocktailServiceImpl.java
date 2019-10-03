package com.ezgroceries.shoppinglist.service;

/*
    Created by Ruben Bernaert (JD68212) on 03/10/2019
*/

import com.ezgroceries.shoppinglist.controller.model.CocktailResource;
import com.ezgroceries.shoppinglist.service.client.CocktailDBClient;
import com.ezgroceries.shoppinglist.service.client.model.CocktailDBResponse;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    // TODO find better implementation
    private List<String> getIngredients(CocktailDBResponse.DrinkResource resource) {
        List<String> ingredients = new ArrayList<>();
        if (!Strings.isNullOrEmpty(resource.getStrIngredient1())) {
            ingredients.add(resource.getStrIngredient1());
        }
        if (!Strings.isNullOrEmpty(resource.getStrIngredient2())) {
            ingredients.add(resource.getStrIngredient2());
        }
        if (!Strings.isNullOrEmpty(resource.getStrIngredient3())) {
            ingredients.add(resource.getStrIngredient3());
        }
        if (!Strings.isNullOrEmpty(resource.getStrIngredient4())) {
            ingredients.add(resource.getStrIngredient4());
        }
        if (!Strings.isNullOrEmpty(resource.getStrIngredient5())) {
            ingredients.add(resource.getStrIngredient5());
        }
        if (!Strings.isNullOrEmpty(resource.getStrIngredient6())) {
            ingredients.add(resource.getStrIngredient6());
        }
        if (!Strings.isNullOrEmpty(resource.getStrIngredient7())) {
            ingredients.add(resource.getStrIngredient7());
        }
        return ingredients;
    }

}
