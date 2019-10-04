package com.ezgroceries.shoppinglist.controller;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.ezgroceries.shoppinglist.controller.model.request.CocktailRequest;
import com.ezgroceries.shoppinglist.controller.model.request.ShoppingListRequest;
import com.ezgroceries.shoppinglist.controller.model.response.CocktailIdResponse;
import com.ezgroceries.shoppinglist.controller.model.response.ShoppingListResponse;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.service.internal.CocktailService;
import com.ezgroceries.shoppinglist.service.internal.ShoppingListService;
import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Shopping lists")
@RequestMapping(value = "/shopping-lists", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingListController {

    private final ShoppingListService shoppingListService;
    private final CocktailService cocktailService;

    @Autowired
    public ShoppingListController(ShoppingListService shoppingListService, CocktailService cocktailService) {
        this.shoppingListService = shoppingListService;
        this.cocktailService = cocktailService;
    }

    @ApiOperation(value = "Get all shopping lists")
    @GetMapping
    public Resources<ShoppingListResponse> getAllShoppingList() {
        List<ShoppingList> shoppingLists = this.shoppingListService.searchAllShoppingLists();
        List<ShoppingListResponse> responses = shoppingLists.stream().map(this::createShoppingListResponseWithIngredients).collect(Collectors.toList());
        return new Resources<>(responses);
    }

    @ApiOperation(value = "Get shopping list by id")
    @GetMapping(value = "/{shoppingListId}")
    public Resource<ShoppingListResponse> getShoppingList(@PathVariable("shoppingListId") String id) {
        ShoppingList shoppingList = this.shoppingListService.searchShoppingList(UUID.fromString(id));
        ShoppingListResponse response = createShoppingListResponseWithIngredients(shoppingList);
        return new Resource<>(response);

    }

    @ApiOperation(value = "Create new shopping list")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Resource<ShoppingListResponse> newShoppingList(@RequestBody ShoppingListRequest list) {
        final ShoppingList shoppingList = this.shoppingListService.create(
                new ShoppingList(
                        UUID.randomUUID(),
                        list.getName(),
                        new HashSet<>()
                )
        );
        return new Resource<>(
                new ShoppingListResponse(
                        shoppingList.getShoppingListId().toString(),
                        shoppingList.getName()
                )
        );
    }

    @ApiOperation(value = "Add cocktails to shopping list by id")
    @PostMapping(value = "/{shoppingListId}/cocktails")
    public Resources<CocktailIdResponse> addToShoppingList(@PathVariable("shoppingListId") String id, @RequestBody List<CocktailRequest> cocktails) {
        return new Resources<>(addCocktailsToShoppingList(id, cocktails));
    }

    private Set<CocktailIdResponse> addCocktailsToShoppingList(String id, List<CocktailRequest> cocktails) {
        if (Strings.isNullOrEmpty(id) || cocktails.isEmpty()) {
            return new HashSet<>();
        }
        final ShoppingList shoppingList = this.shoppingListService.addCocktails(
                UUID.fromString(id),
                cocktails.stream().map(CocktailRequest::getCocktailId).collect(Collectors.toSet())
        );

        // Return only id's
        return shoppingList.getCocktailIds().stream().map(c -> new CocktailIdResponse(c.toString())).collect(Collectors.toSet());
    }

    private ShoppingListResponse createShoppingListResponseWithIngredients(ShoppingList shoppingList) {
        List<String> ingredients = this.cocktailService.searchDistinctIngredients(shoppingList.getCocktailIds());
        return new ShoppingListResponse(
                shoppingList.getShoppingListId().toString(),
                shoppingList.getName(),
                ingredients
        );
    }

}
