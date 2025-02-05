package com.ezgroceries.shoppinglist.persistence.entities;

/*
    Created by Ruben Bernaert (JD68212) on 03/10/2019
*/

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
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
@Table(name = "SHOPPING_LIST")
public class ShoppingListEntity {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @ManyToMany
    @JoinTable(
            name = "COCKTAIL_SHOPPING_LIST",
            joinColumns = @JoinColumn(name = "SHOPPING_LIST_ID"),
            inverseJoinColumns = @JoinColumn(name = "COCKTAIL_ID")
    )
    private List<CocktailEntity> cocktailEntities = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "MEAL_SHOPPING_LIST",
            joinColumns = @JoinColumn(name = "SHOPPING_LIST_ID"),
            inverseJoinColumns = @JoinColumn(name = "MEAL_ID")
    )
    private List<MealEntity> mealEntities = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CocktailEntity> getCocktailEntities() {
        return cocktailEntities;
    }

    public void setCocktailEntities(List<CocktailEntity> cocktailEntities) {
        this.cocktailEntities = cocktailEntities;
    }

    public List<MealEntity> getMealEntities() {
        return mealEntities;
    }

    public void setMealEntities(List<MealEntity> mealEntities) {
        this.mealEntities = mealEntities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, userId, cocktailEntities, mealEntities);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ShoppingListEntity other = (ShoppingListEntity) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.userId, other.userId)
                && Objects.equals(this.cocktailEntities, other.cocktailEntities)
                && Objects.equals(this.mealEntities, other.mealEntities);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("userId", userId)
                .add("cocktailEntities", cocktailEntities)
                .add("mealEntities", mealEntities)
                .toString();
    }

}
