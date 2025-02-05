package com.ezgroceries.shoppinglist.controller.model.response;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class CocktailIdResponse {

    private String cocktailId;

    public CocktailIdResponse(String cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(String cocktailId) {
        this.cocktailId = cocktailId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final CocktailIdResponse other = (CocktailIdResponse) obj;
        return Objects.equals(this.cocktailId, other.cocktailId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("cocktailId", cocktailId)
                .toString();
    }

}
