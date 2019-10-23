package com.ezgroceries.shoppinglist;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.entities.ShoppingListEntity;
import org.assertj.core.util.Lists;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ActiveProfiles(value = "hsqldb")
public abstract class AbstractTest {

    protected String mockedUser = "Test user";

    protected List<CocktailResource> mockedCocktails() {
        return Arrays.asList(
                new CocktailResource(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"),
                        "606",
                        "Margerita",
                        "Cocktail glass",
                        "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                        "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                        new HashSet<>(mockedIngredients())),
                new CocktailResource(
                        UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"),
                        "607",
                        "Blue Margerita",
                        "Cocktail glass",
                        "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                        "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                        new HashSet<>(mockedIngredients())));
    }

    protected List<String> mockedIngredients() {
        List<String> ingredients = new ArrayList<>(5);
        ingredients.add("Tequila");
        ingredients.add("Triple sec");
        ingredients.add("Lime juice");
        ingredients.add("Salt");
        ingredients.add("Blue Curacao");
        return ingredients;
    }

    protected ShoppingList mockedShoppingList() {
        return new ShoppingList(
                UUID.fromString("a494829e-b008-4d2f-b7d6-e185135a8e37"),
                "I'm a mocked shopping list",
                Stream.of(
                        UUID.fromString("bb3b0178-5bd2-48e6-b0cc-e8d83115083f"),
                        UUID.fromString("dc24c940-fdbf-4de4-9531-dd89df76bb08")
                ).collect(Collectors.toSet())
        );
    }

    protected ShoppingListEntity mockedShoppingListEntity() {
        final ShoppingListEntity entity = new ShoppingListEntity();
        entity.setId(UUID.fromString("94511c9b-6ee9-491c-acc3-07e45142ca2f"));
        entity.setName("A - Mocked shopping list entity");
        entity.setCocktailEntities(Lists.newArrayList(
                mockedCocktailEntity("bb3b0178-5bd2-48e6-b0cc-e8d83115083f"),
                mockedCocktailEntity("dc24c940-fdbf-4de4-9531-dd89df76bb08")));
        entity.setUserId(mockedUser);
        return entity;
    }

    protected ShoppingListEntity mockedShoppingListEntityNoCocktails() {
        final ShoppingListEntity entity = new ShoppingListEntity();
        entity.setId(UUID.fromString("ca582a63-bf0e-47a8-a8d6-4aea840a04b0"));
        entity.setName("B - Mocked shopping list entity no cocktails");
        entity.setCocktailEntities(new ArrayList<>());
        entity.setUserId(mockedUser);
        return entity;
    }

    private CocktailEntity mockedCocktailEntity(String uuid) {
        final CocktailEntity entity = new CocktailEntity();
        entity.setId(UUID.fromString(uuid));
        entity.setName("Mocked cocktail entity");
        entity.setIdDrink("609");
        entity.setIngredients(new HashSet<>(mockedIngredients()));
        return entity;
    }

}
