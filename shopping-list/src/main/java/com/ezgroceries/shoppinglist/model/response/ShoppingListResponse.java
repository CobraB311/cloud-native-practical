package com.ezgroceries.shoppinglist.model.response;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

public class ShoppingListResponse {

    private String shoppingListId;
    private String name;
    private List<String> ingredients;

    public ShoppingListResponse(String shoppingListId, String name) {
        this.shoppingListId = shoppingListId;
        this.name = name;
    }

    public ShoppingListResponse(String shoppingListId, String name, List<String> ingredients) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.ingredients = ingredients;
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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingListId, name, ingredients);
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
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.ingredients, other.ingredients);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("shoppingListId", shoppingListId)
                .add("name", name)
                .add("ingredients", ingredients)
                .toString();
    }

}
