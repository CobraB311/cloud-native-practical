package com.ezgroceries.shoppinglist.model;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class ShoppingList {

    private UUID shoppingListId;
    private String name;
    private Set<UUID> cocktailIds;

    public ShoppingList(UUID shoppingListId, String name, Set<UUID> cocktailIds) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.cocktailIds = cocktailIds;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UUID> getCocktailIds() {
        return cocktailIds;
    }

    public void setCocktailIds(Set<UUID> cocktailIds) {
        this.cocktailIds = cocktailIds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingListId, name, cocktailIds);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ShoppingList other = (ShoppingList) obj;
        return Objects.equals(this.shoppingListId, other.shoppingListId)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.cocktailIds, other.cocktailIds);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("shoppingListId", shoppingListId)
                .add("name", name)
                .add("cocktailIds", cocktailIds)
                .toString();
    }

}
