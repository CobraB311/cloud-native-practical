package com.ezgroceries.shoppinglist.controller;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.model.request.ShoppingListRequest;
import com.ezgroceries.shoppinglist.model.response.CocktailIdResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/shopping-lists", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingListController {

    @GetMapping(value = "/{shoppingListId}")
    public Resource<ShoppingListResponse> getShoppingList(@PathVariable("shoppingListId") String id) {
        ShoppingList dummyShoppingList = createDummyShoppingList(
                "90689338-499a-4c49-af90-f1e73068ad4f", "Stephanie's birthday"
        );
        List<String> ingredients = createDummyIngredients();
        ShoppingListResponse response = new ShoppingListResponse(
                dummyShoppingList.getShoppingListId().toString(),
                dummyShoppingList.getName(),
                ingredients
        );
        return new Resource<>(response);

    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Resource<ShoppingListResponse> newShoppingList(@RequestBody ShoppingListRequest list) {
        ShoppingList dummyShoppingList = createDummyShoppingList("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915", list.getName());
        return new Resource<>(
                new ShoppingListResponse(
                        dummyShoppingList.getShoppingListId().toString(),
                        dummyShoppingList.getName()
                )
        );
    }

    @PostMapping(value = "/{shoppingListId}/cocktails")
    public Resources<CocktailIdResponse> addToShoppingList(@PathVariable("shoppingListId") String id, @RequestBody List<CocktailResource> cocktails) {
        return new Resources<>(addCocktailToShoppingList(id, cocktails));
    }

    private ShoppingList createDummyShoppingList(String id, String name) {
        return new ShoppingList(
                UUID.fromString(id),
                name
        );
    }

    private Set<CocktailIdResponse> addCocktailToShoppingList(String id, List<CocktailResource> cocktails) {
        ShoppingList dummyShoppingList = createDummyShoppingList(id, "dummy" + id);
        Set<UUID> cocktailIds = cocktails.stream().map(CocktailResource::getCocktailId).collect(Collectors.toSet());
        dummyShoppingList.setCocktailIds(cocktailIds);

        // Return only id's
        return cocktails.stream().map(c -> new CocktailIdResponse(c.getCocktailId().toString())).collect(Collectors.toSet());
    }

    private List<String> createDummyIngredients() {
        List<String> ingredients =  new ArrayList<>(5);
        ingredients.add("Tequila");
        ingredients.add("Triple sec");
        ingredients.add("Lime juice");
        ingredients.add("Salt");
        ingredients.add("Blue Curacao");
        return ingredients;
    }

}
