package com.ezgroceries.shoppinglist;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.model.MealResource;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.entities.MealEntity;
import com.ezgroceries.shoppinglist.persistence.entities.ShoppingListEntity;
import org.assertj.core.util.Lists;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ActiveProfiles(value = "hsqldb")
public abstract class AbstractTest {

    private static final String COCKTAIL_ID_1 = "23b3d85a-3928-41c0-a533-6538a71e17c4";
    private static final String COCKTAIL_ID_2 = "d615ec78-fe93-467b-8d26-5d26d8eab073";
    private static final String MEAL_ID_1 = "044c881a-b2e3-4851-982f-6d7acce3dfb8";
    protected static final String SHOPPING_LIST_WITH_COCKTAILS = "a494829e-b008-4d2f-b7d6-e185135a8e37";
    protected static final String SHOPPING_LIST_NO_COCKTAILS = "ca582a63-bf0e-47a8-a8d6-4aea840a04b0";

    protected String mockedUser = "Test user";

    protected List<CocktailResource> mockedCocktails() {
        return Lists.newArrayList(
                new CocktailResource(
                        UUID.fromString(COCKTAIL_ID_1),
                        "606",
                        "Margerita",
                        "Cocktail glass",
                        "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                        "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                        new HashSet<>(mockedCocktailIngredients())),
                new CocktailResource(
                        UUID.fromString(COCKTAIL_ID_2),
                        "607",
                        "Blue Margerita",
                        "Cocktail glass",
                        "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                        "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                        new HashSet<>(mockedCocktailIngredients())));
    }

    protected List<MealResource> mockedMeals() {
        return Lists.newArrayList(
                new MealResource(
                        UUID.fromString(MEAL_ID_1),
                        "707",
                        "Lasagne",
                        "Italian",
                        "Italie",
                        "Make the Lasagne",
                        "https://www.themealdb.com/images/media/meals/wtsvxx1511296896.jpg",
                        "youtube",
                        new HashSet<>(mockedMealTags()),
                        new HashSet<>(mockedMealIngredients())
                )
        );
    }

    protected List<String> mockedCocktailIngredients() {
        List<String> ingredients = new ArrayList<>(5);
        ingredients.add("Tequila");
        ingredients.add("Triple sec");
        ingredients.add("Lime juice");
        ingredients.add("Salt");
        ingredients.add("Blue Curacao");
        return ingredients.stream().sorted().collect(Collectors.toList());
    }

    protected List<String> mockedMealIngredients() {
        List<String> ingredients = new ArrayList<>(3);
        ingredients.add("Mozzarella");
        ingredients.add("Tomatoes");
        ingredients.add("Salt");
        return ingredients.stream().sorted().collect(Collectors.toList());
    }

    protected List<String> mockedMealTags() {
        List<String> tags = new ArrayList<>(3);
        tags.add("Pasta");
        tags.add("Italian");
        tags.add("Oven");
        return tags;
    }

    protected ShoppingList mockedShoppingList() {
        return new ShoppingList(
                UUID.fromString(SHOPPING_LIST_WITH_COCKTAILS),
                "I'm a mocked shopping list",
                mockedUser,
                Stream.of(
                        UUID.fromString(COCKTAIL_ID_1),
                        UUID.fromString(COCKTAIL_ID_2)
                ).collect(Collectors.toSet()),
                Stream.of(
                        UUID.fromString(MEAL_ID_1)
                ).collect(Collectors.toSet())
        );
    }

    protected ShoppingListEntity mockedShoppingListEntity() {
        final ShoppingListEntity entity = new ShoppingListEntity();
        entity.setId(UUID.fromString(SHOPPING_LIST_WITH_COCKTAILS));
        entity.setName("A - Mocked shopping list entity");
        entity.setCocktailEntities(Lists.newArrayList(
                mockedCocktailEntity(COCKTAIL_ID_1),
                mockedCocktailEntity(COCKTAIL_ID_2)));
        entity.setMealEntities(Lists.newArrayList(
                mockedMealEntity(MEAL_ID_1)
        ));
        entity.setUserId(mockedUser);
        return entity;
    }

    protected ShoppingListEntity mockedShoppingListEntityNoCocktails() {
        final ShoppingListEntity entity = new ShoppingListEntity();
        entity.setId(UUID.fromString(SHOPPING_LIST_NO_COCKTAILS));
        entity.setName("B - Mocked shopping list entity no cocktails");
        entity.setCocktailEntities(new ArrayList<>());
        entity.setUserId(mockedUser);
        return entity;
    }

    private CocktailEntity mockedCocktailEntity(String uuid) {
        CocktailEntity entity = new CocktailEntity();
        entity.setId(UUID.fromString(uuid));
        entity.setName("Mocked cocktail entity");
        entity.setIdDrink("609");
        entity.setIngredients(new HashSet<>(mockedCocktailIngredients()));
        return entity;
    }

    private MealEntity mockedMealEntity(String uuid) {
        MealEntity entity = new MealEntity();
        entity.setId(UUID.fromString(uuid));
        entity.setName("Mocked meal entity");
        entity.setIdMeal("709");
        entity.setYoutubeLink("youtube");
        entity.setImageLink("image");
        entity.setInstructions("instructions");
        entity.setArea("area");
        entity.setCategory("category");
        entity.setTags(new HashSet<>(mockedMealTags()));
        entity.setIngredients(new HashSet<>(mockedMealIngredients()));
        return entity;
    }

}
