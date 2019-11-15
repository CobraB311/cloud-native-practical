package com.ezgroceries.shoppinglist.persistence.entities;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
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
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "MEAL")
public class MealEntity {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "ID_MEAL", unique = true)
    private String idMeal;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "AREA")
    private String area;

    @Column(name = "INSTRUCTIONS")
    private String instructions;

    @Column(name = "IMAGE_LINK")
    private String imageLink;

    @Column(name = "YOUTUBE_LINK")
    private String youtubeLink;

    @Convert(converter = StringSetConverter.class)
    @Column(name = "TAGS")
    private Set<String> tags;

    @Convert(converter = StringSetConverter.class)
    @Column(name = "INGREDIENTS")
    private Set<String> ingredients;

    @ManyToMany
    @JoinTable(
            name = "MEAL_SHOPPING_LIST",
            joinColumns = @JoinColumn(name = "MEAL_ID"),
            inverseJoinColumns = @JoinColumn(name = "SHOPPING_LIST_ID")
    )
    private List<ShoppingListEntity> shoppingListEntities = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
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

    public List<ShoppingListEntity> getShoppingListEntities() {
        return shoppingListEntities;
    }

    public void setShoppingListEntities(List<ShoppingListEntity> shoppingListEntities) {
        this.shoppingListEntities = shoppingListEntities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idMeal, name, category, area, instructions, imageLink, youtubeLink, tags, ingredients, shoppingListEntities);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MealEntity other = (MealEntity) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.idMeal, other.idMeal)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.category, other.category)
                && Objects.equals(this.area, other.area)
                && Objects.equals(this.instructions, other.instructions)
                && Objects.equals(this.imageLink, other.imageLink)
                && Objects.equals(this.youtubeLink, other.youtubeLink)
                && Objects.equals(this.tags, other.tags)
                && Objects.equals(this.ingredients, other.ingredients)
                && Objects.equals(this.shoppingListEntities, other.shoppingListEntities);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("idMeal", idMeal)
                .add("name", name)
                .add("category", category)
                .add("area", area)
                .add("instructions", instructions)
                .add("imageLink", imageLink)
                .add("youtubeLink", youtubeLink)
                .add("tags", tags)
                .add("ingredients", ingredients)
                .add("shoppingListEntities", shoppingListEntities)
                .toString();
    }

}
