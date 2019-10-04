package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.ShoppingList;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.UUID;

public interface ShoppingListService {

    ShoppingList create(@Nonnull ShoppingList shoppingList);

    ShoppingList addCocktails(@Nonnull UUID shoppingListId, Set<UUID> cocktailIds);

}
