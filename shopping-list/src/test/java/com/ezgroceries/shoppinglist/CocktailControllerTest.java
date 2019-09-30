package com.ezgroceries.shoppinglist;
/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CocktailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {
        this.mockMvc.perform(get("/cocktails")
                .param("search", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("{\"_embedded\":{\"cocktailResourceList\":[{\"cocktailId\":\"23b3d85a-3928-41c0-a533-6538a71e17c4\",\"name\":\"Margerita\",\"glass\":\"Cocktail glass\",\"instructions\":\"Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..\",\"image\":\"https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg\",\"ingredients\":[\"Tequila\",\"Triple sec\",\"Lime juice\",\"Salt\"]},{\"cocktailId\":\"d615ec78-fe93-467b-8d26-5d26d8eab073\",\"name\":\"Blue Margerita\",\"glass\":\"Cocktail glass\",\"instructions\":\"Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..\",\"image\":\"https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg\",\"ingredients\":[\"Tequila\",\"Blue Curacao\",\"Lime juice\",\"Salt\"]}]}}"));
    }

}
