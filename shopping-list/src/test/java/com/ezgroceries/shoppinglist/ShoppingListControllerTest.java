package com.ezgroceries.shoppinglist;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingListControllerTest {

    private final String rootMapping = "/shopping-lists";

    @Autowired
    private MockMvc mockMvc;

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
        ;
    }

    @Test
    public void testAddToShoppingList() throws Exception {
        this.mockMvc.perform(
                post(rootMapping + "/"+  UUID.randomUUID().toString() + "/cocktails")
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

}
