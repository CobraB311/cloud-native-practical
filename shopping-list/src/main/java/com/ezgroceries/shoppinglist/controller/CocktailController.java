package com.ezgroceries.shoppinglist.controller;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.ezgroceries.shoppinglist.controller.model.CocktailResource;
import com.ezgroceries.shoppinglist.service.CocktailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return new Resources<>(this.cocktailService.searchCocktails(search));
    }

}

