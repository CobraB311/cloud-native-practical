package com.ezgroceries.shoppinglist.persistence.entities;

/*
    Created by Ruben Bernaert (JD68212) on 03/10/2019
*/

import com.google.common.base.MoreObjects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "COCKTAIL_SHOPPING_LIST")
public class CocktailShoppingList {

    @ManyToMany(targetEntity = CocktailEntity.class)
    @JoinColumn(name = "COCKTAIL_ID", referencedColumnName = "ID", table = "COCKTAIL")
    private List<CocktailEntity> cocktailEntities;

    @ManyToMany(targetEntity = ShoppingListEntity.class)
    @JoinColumn(name = "SHOPPING_LIST_ID", referencedColumnName = "ID", table = "SHOPPING_LIST")
    private List<ShoppingListEntity> shoppingListEntities;

    public List<CocktailEntity> getCocktailEntities() {
        return cocktailEntities;
    }

    public void setCocktailEntities(List<CocktailEntity> cocktailEntities) {
        this.cocktailEntities = cocktailEntities;
    }

    public List<ShoppingListEntity> getShoppingListEntities() {
        return shoppingListEntities;
    }

    public void setShoppingListEntities(List<ShoppingListEntity> shoppingListEntities) {
        this.shoppingListEntities = shoppingListEntities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailEntities, shoppingListEntities);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final CocktailShoppingList other = (CocktailShoppingList) obj;
        return Objects.equals(this.cocktailEntities, other.cocktailEntities)
                && Objects.equals(this.shoppingListEntities, other.shoppingListEntities);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("cocktailEntities", cocktailEntities)
                .add("shoppingListEntities", shoppingListEntities)
                .toString();
    }

}
