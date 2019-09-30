package com.ezgroceries.shoppinglist.controller;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.ezgroceries.shoppinglist.model.ShoppingList;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/shopping-lists", produces = MediaType.APPLICATION_JSON_VALUE)
public class ShoppingListController {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Resource<ShoppingList> newShoppingList(@RequestBody String name) {
        return new Resource<>(createDummyShoppingList(name));
    }

    private ShoppingList createDummyShoppingList(String name) {
        return new ShoppingList(
                UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"),
                "Stephanie's birthday"
        );
    }

}
