package com.ezgroceries.shoppinglist.persistence.entities;

/*
    Created by Ruben Bernaert (JD68212) on 03/10/2019
*/

import com.ezgroceries.shoppinglist.persistence.entities.util.StringSetConverter;
import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "COCKTAIL")
public class CocktailEntity {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "ID_DRINK", unique = true)
    private String idDrink;

    @Column(name = "NAME")
    private String name;

    @Convert(converter = StringSetConverter.class)
    @Column(name = "INGREDIENTS")
    private List<String> ingredients;

    @ManyToMany
    @JoinTable(
            name = "COCKTAIL_SHOPPING_LIST",
            joinColumns = @JoinColumn(name = "COCKTAIL_ID"),
            inverseJoinColumns = @JoinColumn(name = "SHOPPING_LIST_ID")
    )
    private List<ShoppingListEntity> shoppingListEntities = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
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

    public List<ShoppingListEntity> getShoppingListEntities() {
        return shoppingListEntities;
    }

    public void setShoppingListEntities(List<ShoppingListEntity> shoppingListEntities) {
        this.shoppingListEntities = shoppingListEntities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idDrink, name, ingredients, shoppingListEntities);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final CocktailEntity other = (CocktailEntity) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.idDrink, other.idDrink)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.ingredients, other.ingredients)
                && Objects.equals(this.shoppingListEntities, other.shoppingListEntities);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("idDrink", idDrink)
                .add("name", name)
                .add("ingredients", ingredients)
                .add("shoppingListEntities", shoppingListEntities)
                .toString();
    }

}
