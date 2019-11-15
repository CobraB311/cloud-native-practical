package com.ezgroceries.shoppinglist.controller;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.ezgroceries.shoppinglist.AbstractTest;
import com.ezgroceries.shoppinglist.service.internal.CocktailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CocktailControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocktailService cocktailService;

    @Before
    public void initialize() {
        when(cocktailService.searchCocktails(anyString())).thenReturn(mockedCocktails());
    }

    @Test
    public void testGet() throws Exception {
        this.mockMvc.perform(get("/cocktails")
                .param("search", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(
                        jsonPath("$._embedded.cocktailResourceList",
                                jsonPath("cockTailId").exists(),
                                jsonPath("name").exists(),
                                jsonPath("glass").exists(),
                                jsonPath("instructions").exists(),
                                jsonPath("image").exists(),
                                jsonPath("ingredients").isArray()
                        ).exists());
    }

}
