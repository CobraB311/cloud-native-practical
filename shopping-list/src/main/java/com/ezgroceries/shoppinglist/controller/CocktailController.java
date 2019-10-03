package com.ezgroceries.shoppinglist.controller;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.ezgroceries.shoppinglist.controller.model.CocktailResource;
import com.ezgroceries.shoppinglist.service.CocktailService;
import com.ezgroceries.shoppinglist.service.client.model.CocktailDBResponse;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Cocktails")
@RequestMapping(value = "/cocktails", produces = "application/json")
public class CocktailController {

    private final CocktailService cocktailService;

    @Autowired
    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @ApiOperation(value = "Get all cocktails")
    @GetMapping
    public Resources<CocktailResource> get(@RequestParam String search) {
        return new Resources<>(getCocktails(search));
    }

    private List<CocktailResource> getCocktails(String search) {
        final CocktailDBResponse response = this.cocktailService.searchCocktails(search);

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

