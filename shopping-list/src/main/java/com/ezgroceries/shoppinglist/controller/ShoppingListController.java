package com.ezgroceries.shoppinglist.controller;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.model.request.CocktailRequest;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/shopping-lists", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingListController {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Resource<ShoppingList> newShoppingList(@RequestBody String name) {
        return new Resource<>(
                createDummyShoppingList("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915", "Stephanie's birthday")
        );
    }

    @PostMapping(value = "/{shoppingListId}/cocktails")
    public Resources<CocktailRequest> addToShoppingList(@PathVariable("shoppingListId") String id, @RequestBody List<CocktailRequest> cocktails) {
        return new Resources<>(addCocktailToShoppingList(id, cocktails));
    }

    private ShoppingList createDummyShoppingList(String id, String name) {
        return new ShoppingList(
                UUID.fromString(id),
                name
        );
    }

    private List<CocktailRequest> addCocktailToShoppingList(String id, List<CocktailRequest> cocktails) {
        ShoppingList dummyShoppingList = createDummyShoppingList(id, "dummy" + id);
        Set<UUID> cocktailIds = cocktails.stream().map(c -> UUID.fromString(c.getCocktailId())).collect(Collectors.toSet());
        dummyShoppingList.setCocktailIds(cocktailIds);
        return cocktails;
    }

}
