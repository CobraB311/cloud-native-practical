package com.ezgroceries.shoppinglist.model;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.UUID;

public class CocktailResource {

    private UUID cocktailId;
    private String name;
    private String glass;
    private String instructions;
    private String image;
    private Set<String> ingredients;

    public CocktailResource(UUID cocktailId, String name, String glass, String instructions, String image, Set<String> ingredients) {
        this.cocktailId = cocktailId;
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

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cocktailId, name, glass, instructions, image, ingredients);
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
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.glass, other.glass)
                && Objects.equals(this.instructions, other.instructions)
                && Objects.equals(this.image, other.image)
                && Objects.equals(this.ingredients, other.ingredients);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CocktailResource.class.getSimpleName() + "[", "]")
                .add("cocktailId=" + cocktailId)
                .add("name='" + name + "'")
                .add("glass='" + glass + "'")
                .add("instructions='" + instructions + "'")
                .add("image='" + image + "'")
                .add("ingredients=" + ingredients)
                .toString();
    }

}
