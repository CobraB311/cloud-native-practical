package com.ezgroceries.shoppinglist;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.service.internal.CocktailService;
import com.ezgroceries.shoppinglist.service.internal.ShoppingListService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingListControllerTest extends AbstractTest {

    private final String rootMapping = "/shopping-lists";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailService cocktailService;

    @MockBean
    private ShoppingListService shoppingListService;

    @Before
    public void initialize() {
        when(cocktailService.searchCocktails(anyString())).thenReturn(mockedCocktails());
        when(shoppingListService.create(any())).thenReturn(mockedShoppingList());
        when(shoppingListService.addCocktails(any(), anySet())).thenReturn(mockedShoppingList());
        when(shoppingListService.searchShoppingList(any())).thenReturn(mockedShoppingList());
        when(cocktailService.searchDistinctIngredients(any())).thenReturn(mockedIngredients());
        when(shoppingListService.searchAllShoppingLists()).thenReturn(Lists.newArrayList(mockedShoppingList(), mockedShoppingList()));
    }

    @Test
    public void testGetAllShoppingList() throws Exception {
        this.mockMvc.perform(
                get(rootMapping)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$._embedded.shoppingListResponseList",
                        jsonPath("shoppingListId").exists(),
                        jsonPath("name").exists(),
                        jsonPath("ingredients").isArray()
                ).exists())
                .andExpect(content().json("{\n" +
                        "  \"_embedded\": {\n" +
                        "    \"shoppingListResponseList\": [\n" +
                        "      {\n" +
                        "        \"shoppingListId\": \"a494829e-b008-4d2f-b7d6-e185135a8e37\",\n" +
                        "        \"name\": \"I'm a mocked shopping list\",\n" +
                        "        \"ingredients\": [\n" +
                        "          \"Tequila\",\n" +
                        "          \"Triple sec\",\n" +
                        "          \"Lime juice\",\n" +
                        "          \"Salt\",\n" +
                        "          \"Blue Curacao\"\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"shoppingListId\": \"a494829e-b008-4d2f-b7d6-e185135a8e37\",\n" +
                        "        \"name\": \"I'm a mocked shopping list\",\n" +
                        "        \"ingredients\": [\n" +
                        "          \"Tequila\",\n" +
                        "          \"Triple sec\",\n" +
                        "          \"Lime juice\",\n" +
                        "          \"Salt\",\n" +
                        "          \"Blue Curacao\"\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  }\n" +
                        "}"))
        ;
    }

    @Test
    public void testGetShoppingList() throws Exception {
        this.mockMvc.perform(
                get(rootMapping + "/" + UUID.randomUUID().toString())
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.shoppingListId").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.ingredients").isArray())
                .andExpect(content().json("{\n" +
                        "  \"shoppingListId\": \"a494829e-b008-4d2f-b7d6-e185135a8e37\",\n" +
                        "  \"name\": \"I'm a mocked shopping list\",\n" +
                        "  \"ingredients\": [\n" +
                        "          \"Tequila\",\n" +
                        "          \"Triple sec\",\n" +
                        "          \"Lime juice\",\n" +
                        "          \"Salt\",\n" +
                        "          \"Blue Curacao\"\n" +
                        "        ]\n" +
                        "}"))
        ;
    }

    @Test
    public void testNewShoppingList() throws Exception {
        this.mockMvc.perform(
                post(rootMapping)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("{\"name\": \"Stephanie's birthday\"}")
        )
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.shoppingListId").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(content().json("{\n" +
                        "  \"shoppingListId\": \"a494829e-b008-4d2f-b7d6-e185135a8e37\",\n" +
                        "  \"name\": \"I'm a mocked shopping list\",\n" +
                        "  \"ingredients\": null\n" +
                        "}"))
        ;
    }

    @Test
    public void testAddToShoppingList() throws Exception {
        this.mockMvc.perform(
                post(rootMapping + "/" + UUID.randomUUID().toString() + "/cocktails")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("[\n" +
                                "  {\n" +
                                "    \"cocktailId\": \"23b3d85a-3928-41c0-a533-6538a71e17c4\"\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"cocktailId\": \"d615ec78-fe93-467b-8d26-5d26d8eab073\"\n" +
                                "  }\n" +
                                "]")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$._embedded.cocktailIdResponseList",
                        jsonPath("cocktailId").exists()
                ).isArray())
        ;
    }

    private ShoppingList mockedShoppingList() {
        return new ShoppingList(
                UUID.fromString("a494829e-b008-4d2f-b7d6-e185135a8e37"),
                "I'm a mocked shopping list",
                Stream.of(
                        UUID.fromString("bb3b0178-5bd2-48e6-b0cc-e8d83115083f")
                ).collect(Collectors.toSet())
        );
    }

    private List<String> mockedIngredients() {
        List<String> ingredients = new ArrayList<>(5);
        ingredients.add("Tequila");
        ingredients.add("Triple sec");
        ingredients.add("Lime juice");
        ingredients.add("Salt");
        ingredients.add("Blue Curacao");
        return ingredients;
    }

}
