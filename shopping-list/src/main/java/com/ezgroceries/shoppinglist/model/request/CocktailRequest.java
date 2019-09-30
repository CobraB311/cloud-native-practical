package com.ezgroceries.shoppinglist.model.request;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import java.util.Objects;
import java.util.StringJoiner;

public class CocktailRequest {

    private String cocktailId;

    public CocktailRequest(String cocktailId) {
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
        final CocktailRequest other = (CocktailRequest) obj;
        return Objects.equals(this.cocktailId, other.cocktailId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CocktailRequest.class.getSimpleName() + "[", "]")
                .add("cocktailId='" + cocktailId + "'")
                .toString();
    }

}
