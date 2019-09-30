package com.ezgroceries.shoppinglist.model.response;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import java.util.Objects;
import java.util.StringJoiner;

public class ShoppingListResponse {

    private String shoppingListId;
    private String name;

    public ShoppingListResponse(String shoppingListId, String name) {
        this.shoppingListId = shoppingListId;
        this.name = name;
    }

    public String getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(String shoppingListId) {
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
        final ShoppingListResponse other = (ShoppingListResponse) obj;
        return Objects.equals(this.shoppingListId, other.shoppingListId)
                && Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ShoppingListResponse.class.getSimpleName() + "[", "]")
                .add("shoppingListId='" + shoppingListId + "'")
                .add("name='" + name + "'")
                .toString();
    }

}
