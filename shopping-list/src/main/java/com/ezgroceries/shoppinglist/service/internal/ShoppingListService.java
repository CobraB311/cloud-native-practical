package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.ShoppingList;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ShoppingListService {

    ShoppingList create(@Nonnull String name);

    @PreAuthorize("@shoppingListServiceImpl.searchShoppingList(#shoppingListId).userName.equals(authentication.name)")
    ShoppingList addCocktails(@Nonnull UUID shoppingListId, Set<UUID> cocktailIds);

    @PostAuthorize("returnObject.userName.equals(authentication.name)")
    ShoppingList searchShoppingList(@Nonnull UUID id);

    List<ShoppingList> searchAllShoppingLists();

}
