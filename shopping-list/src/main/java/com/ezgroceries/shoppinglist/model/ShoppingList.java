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
    private String userName;
    private Set<UUID> cocktailIds;
    private Set<UUID> mealIds;

    public ShoppingList(UUID shoppingListId, String name, Set<UUID> cocktailIds, Set<UUID> mealIds) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.cocktailIds = cocktailIds;
        this.mealIds = mealIds;
    }

    public ShoppingList(UUID shoppingListId, String name, String userName, Set<UUID> cocktailIds, Set<UUID> mealIds) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.userName = userName;
        this.cocktailIds = cocktailIds;
        this.mealIds = mealIds;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<UUID> getCocktailIds() {
        return cocktailIds;
    }

    public void setCocktailIds(Set<UUID> cocktailIds) {
        this.cocktailIds = cocktailIds;
    }

    public Set<UUID> getMealIds() {
        return mealIds;
    }

    public void setMealIds(Set<UUID> mealIds) {
        this.mealIds = mealIds;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingListId, name, userName, cocktailIds, mealIds);
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
                && Objects.equals(this.userName, other.userName)
                && Objects.equals(this.cocktailIds, other.cocktailIds)
                && Objects.equals(this.mealIds, other.mealIds);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("shoppingListId", shoppingListId)
                .add("name", name)
                .add("userName", userName)
                .add("cocktailIds", cocktailIds)
                .add("mealIds", mealIds)
                .toString();
    }

}
