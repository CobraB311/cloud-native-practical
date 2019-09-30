package com.ezgroceries.shoppinglist.model;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

public class ShoppingList {

    private UUID shoppingListId;
    private String name;

    public ShoppingList(UUID shoppingListId, String name) {
        this.shoppingListId = shoppingListId;
        this.name = name;
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

    @Override
    public int hashCode() {
        return Objects.hash(shoppingListId, name);
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
                && Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ShoppingList.class.getSimpleName() + "[", "]")
                .add("shoppingListId=" + shoppingListId)
                .add("name='" + name + "'")
                .toString();
    }

}
