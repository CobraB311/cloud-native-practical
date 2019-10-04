package com.ezgroceries.shoppinglist.controller.model.request;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.UUID;

public class CocktailRequest {

    private UUID cocktailId;

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
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
        final CocktailRequest other = (CocktailRequest) obj;
        return Objects.equals(this.cocktailId, other.cocktailId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("cocktailId", cocktailId)
                .toString();
    }

}
