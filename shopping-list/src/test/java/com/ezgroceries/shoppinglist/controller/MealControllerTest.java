package com.ezgroceries.shoppinglist.controller;

/*
    Created by Ruben Bernaert (JD68212) on 06/11/2019
*/

import com.ezgroceries.shoppinglist.AbstractTest;
import com.ezgroceries.shoppinglist.model.MealResource;
import com.ezgroceries.shoppinglist.service.internal.MealService;
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

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MealControllerTest extends AbstractTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MealService mealService;

    @Before
    public void initialize() {
        when(mealService.searchMeals(anyString())).thenReturn(mockedMeals());
    }

    @Test
    public void testGet() throws Exception {

        final MealResource meal0 = mockedMeals().get(0);

        this.mockMvc.perform(get("/meals")
                .param("search", "Test")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(
                        jsonPath("$._embedded.mealResourceList",
                                jsonPath("mealUuid").value(meal0.getMealUuid()),
                                jsonPath("mealId").value(meal0.getMealId()),
                                jsonPath("name").value(meal0.getName()),
                                jsonPath("category").value(meal0.getCategory()),
                                jsonPath("area").value(meal0.getArea()),
                                jsonPath("instructions").value(meal0.getInstructions()),
                                jsonPath("image").value(meal0.getImage()),
                                jsonPath("youtube").value(meal0.getYoutube()),
                                jsonPath("tags").isArray(),
                                jsonPath("tags").value(meal0.getTags()),
                                jsonPath("ingredients").isArray(),
                                jsonPath("ingredients").value(meal0.getIngredients())
                        ).exists()
                );
    }

}
