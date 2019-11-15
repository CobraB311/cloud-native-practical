package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 07/10/2019
*/

import com.ezgroceries.shoppinglist.AbstractTest;
import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.service.external.CocktailExtService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

public class CocktailServiceTest extends AbstractTest {

    @Mock
    private CocktailRepository cocktailRepository;

    @Mock
    private CocktailExtService cocktailExtService;

    private CocktailService cocktailService;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);

        this.cocktailService = new CocktailServiceImpl(cocktailRepository, cocktailExtService);

        when(cocktailExtService.searchCocktails("test")).thenReturn(mockedCocktails());
        when(cocktailRepository.findAllById(anySet())).thenReturn(mockedShoppingListEntity().getCocktailEntities());
    }

    @Test
    public void searchCocktails() {
        final List<CocktailResource> cocktails = cocktailService.searchCocktails("test");
        checkCocktails(cocktails);
    }

    @Test
    public void searchDistinctIngredients() {
        final List<String> ingredients = cocktailService.searchDistinctIngredients(mockedShoppingListEntity().getCocktailEntities().stream().map(CocktailEntity::getId).collect(Collectors.toSet()));
        assertEquals(mockedCocktailIngredients().size(), ingredients.size());
        assertTrue(mockedCocktailIngredients().containsAll(ingredients));
    }

    private void checkCocktails(List<CocktailResource> cocktails) {
        assertEquals(mockedCocktails().size(), cocktails.size());
        List<CocktailResource> mockedCocktails = mockedCocktails();

        cocktails.sort(Comparator.comparing(CocktailResource::getDrinkId));
        mockedCocktails.sort(Comparator.comparing(CocktailResource::getDrinkId));

        assertEquals(cocktails.size(), 2);

        checkCocktail(mockedCocktails.get(0), cocktails.get(0));
        checkCocktail(mockedCocktails.get(1), cocktails.get(1));
    }

    private void checkCocktail(CocktailResource expected, CocktailResource actual) {
        assertNotNull(actual.getCocktailId()); // Cannot assert equals because of random UUID while creating
        assertEquals(expected.getDrinkId(), actual.getDrinkId());
        assertEquals(expected.getGlass(), actual.getGlass());
        assertEquals(expected.getImage(), actual.getImage());
        assertEquals(expected.getInstructions(), actual.getInstructions());
        assertEquals(expected.getName(), actual.getName());
        checkIngredients(expected.getIngredients(), actual.getIngredients());
    }

    private void checkIngredients(Set<String> expected, Set<String> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(expected.containsAll(actual));
    }

}
