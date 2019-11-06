package com.ezgroceries.shoppinglist.controller;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.ezgroceries.shoppinglist.AbstractTest;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

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
        when(shoppingListService.addMeals(any(), anySet())).thenReturn(mockedShoppingList());
        when(shoppingListService.searchShoppingList(any())).thenReturn(mockedShoppingList());
        when(cocktailService.searchDistinctIngredients(any())).thenReturn(mockedCocktailIngredients());
        when(shoppingListService.searchAllShoppingLists()).thenReturn(Lists.newArrayList(mockedShoppingList(), mockedShoppingList()));
    }

    @Test
    @WithMockUser
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
    @WithMockUser
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
    @WithMockUser
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
    @WithMockUser
    public void testAddCocktailsToShoppingList() throws Exception {
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

    @Test
    @WithMockUser
    public void testAddMealsToShoppingList() throws Exception {
        this.mockMvc.perform(
                post(rootMapping + "/" + UUID.randomUUID().toString() + "/meals")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content("[\n" +
                                "  {\n" +
                                "    \"mealId\": \"23b3d85a-3928-41c0-a533-6538a71e17c4\"\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"mealId\": \"d615ec78-fe93-467b-8d26-5d26d8eab073\"\n" +
                                "  }\n" +
                                "]")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$._embedded.mealIdResponseList",
                        jsonPath("mealId").value("45ff387d-4b84-4df9-a76e-9adebd6f9082")
                ).isArray())
        ;
    }

}
