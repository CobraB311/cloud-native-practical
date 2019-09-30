package com.ezgroceries.shoppinglist.controller;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.model.response.CocktailId;
import com.ezgroceries.shoppinglist.model.response.ShoppingListResponse;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/shopping-lists", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingListController {

    @GetMapping(value = "/{shoppingListId}")
    public Resource<ShoppingList> getShoppingList(@PathVariable("shoppingListId") String id) {
        return new Resource<>(new ShoppingList(UUID.fromString(id), "")); // TODO
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Resource<ShoppingListResponse> newShoppingList(@RequestBody Map<String, String> name) { // TODO Create request class for this
        ShoppingList dummyShoppingList = createDummyShoppingList("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915", name.get("name"));
        return new Resource<>(
                new ShoppingListResponse(
                        dummyShoppingList.getShoppingListId().toString(),
                        dummyShoppingList.getName()
                )
        );
    }

    @PostMapping(value = "/{shoppingListId}/cocktails")
    public Resources<CocktailId> addToShoppingList(@PathVariable("shoppingListId") String id, @RequestBody List<CocktailResource> cocktails) {
        return new Resources<>(addCocktailToShoppingList(id, cocktails));
    }

    private ShoppingList createDummyShoppingList(String id, String name) {
        return new ShoppingList(
                UUID.fromString(id),
                name
        );
    }

    private Set<CocktailId> addCocktailToShoppingList(String id, List<CocktailResource> cocktails) {
        ShoppingList dummyShoppingList = createDummyShoppingList(id, "dummy" + id);
        Set<UUID> cocktailIds = cocktails.stream().map(CocktailResource::getCocktailId).collect(Collectors.toSet());
        dummyShoppingList.setCocktailIds(cocktailIds);

        // Return only id's
        return cocktails.stream().map(c -> new CocktailId(c.getCocktailId().toString())).collect(Collectors.toSet());
    }

}
