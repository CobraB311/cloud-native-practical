package com.ezgroceries.shoppinglist.model;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CocktailResource {

    private UUID cocktailId;
    private String drinkId;
    private String name;
    private String glass;
    private String instructions;
    private String image;
    private List<String> ingredients;

    public CocktailResource(UUID cocktailId, String drinkId, String name, String glass, String instructions, String image, List<String> ingredients) {
        this.cocktailId = cocktailId;
        this.drinkId = drinkId;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
        this.ingredients = ingredients;
    }

    public CocktailResource() {
    }

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(String drinkId) {
        this.drinkId = drinkId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailId, drinkId, name, glass, instructions, image, ingredients);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final CocktailResource other = (CocktailResource) obj;
        return Objects.equals(this.cocktailId, other.cocktailId)
                && Objects.equals(this.drinkId, other.drinkId)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.glass, other.glass)
                && Objects.equals(this.instructions, other.instructions)
                && Objects.equals(this.image, other.image)
                && Objects.equals(this.ingredients, other.ingredients);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("cocktailId", cocktailId)
                .add("drinkId", drinkId)
                .add("name", name)
                .add("glass", glass)
                .add("instructions", instructions)
                .add("image", image)
                .add("ingredients", ingredients)
                .toString();
    }

}
