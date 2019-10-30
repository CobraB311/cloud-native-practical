package com.ezgroceries.shoppinglist.model;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
*/

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class MealResource {

    private UUID mealUuid;
    private String mealId;
    private String name;
    private String category;
    private String area;
    private String instructions;
    private String image;
    private String youtube;
    private Set<String> tags;
    private Set<String> ingredients;

    public MealResource(UUID mealUuid, String mealId, String name, String category, String area, String instructions, String image, String youtube, Set<String> tags, Set<String> ingredients) {
        this.mealUuid = mealUuid;
        this.mealId = mealId;
        this.name = name;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.image = image;
        this.youtube = youtube;
        this.tags = tags;
        this.ingredients = ingredients;
    }

    public UUID getMealUuid() {
        return mealUuid;
    }

    public void setMealUuid(UUID mealUuid) {
        this.mealUuid = mealUuid;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealUuid, mealId, name, category, area, instructions, image, youtube, tags, ingredients);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MealResource other = (MealResource) obj;
        return Objects.equals(this.mealUuid, other.mealUuid)
                && Objects.equals(this.mealId, other.mealId)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.category, other.category)
                && Objects.equals(this.area, other.area)
                && Objects.equals(this.instructions, other.instructions)
                && Objects.equals(this.image, other.image)
                && Objects.equals(this.youtube, other.youtube)
                && Objects.equals(this.tags, other.tags)
                && Objects.equals(this.ingredients, other.ingredients);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("mealUuid", mealUuid)
                .add("mealId", mealId)
                .add("name", name)
                .add("category", category)
                .add("area", area)
                .add("instructions", instructions)
                .add("image", image)
                .add("youtube", youtube)
                .add("tags", tags)
                .add("ingredients", ingredients)
                .toString();
    }

}
