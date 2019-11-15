package com.ezgroceries.shoppinglist.service.external;

/*
    Created by Ruben Bernaert (JD68212) on 03/10/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.service.external.client.CocktailDBClient;
import com.ezgroceries.shoppinglist.service.external.client.model.CocktailDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CocktailExtServiceImpl implements CocktailExtService {

    private final CocktailDBClient cocktailDBClient;

    @Autowired
    public CocktailExtServiceImpl(CocktailDBClient cocktailDBClient) {
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
                d.getIdDrink(), // TODO - Should we return this to FE?
                d.getStrDrink(),
                d.getStrGlass(),
                d.getStrInstructions(),
                d.getStrDrinkThumb(),
                d.getIngredients()
        )).collect(Collectors.toList());
    }

}
