package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.AbstractTest;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.persistence.repositories.ShoppingListRepository;
import com.ezgroceries.shoppinglist.security.user.AuthenticationFacade;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ShoppingListServiceTest extends AbstractTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private CocktailService cocktailService;

    @Mock
    private MealService mealService;

    @Mock
    private AuthenticationFacade authenticationFacade;

    private ShoppingListService shoppingListService;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);

        shoppingListService = new ShoppingListServiceImpl(shoppingListRepository, cocktailService, mealService, authenticationFacade);

        when(authenticationFacade.getUserName()).thenReturn(mockedUser);

        when(shoppingListRepository.findByName(anyString())).thenReturn(mockedShoppingListEntity());
        when(shoppingListRepository.save(any(ShoppingListEntity.class))).thenReturn(mockedShoppingListEntity());

        when(shoppingListRepository.findById(UUID.fromString(SHOPPING_LIST_WITH_COCKTAILS)))
                .thenReturn(Optional.of(mockedShoppingListEntity()));
        when(shoppingListRepository.findById(UUID.fromString(SHOPPING_LIST_NO_COCKTAILS)))
                .thenReturn(Optional.of(mockedShoppingListEntityNoCocktails()));
        when(cocktailService.findCocktails(mockedShoppingList().getCocktailIds())).thenReturn(mockedCocktails());

        List<ShoppingListEntity> shoppingListEntities = Lists.newArrayList(mockedShoppingListEntityNoCocktails(), mockedShoppingListEntity());
        shoppingListEntities.sort(Comparator.comparing(ShoppingListEntity::getName));
        when(shoppingListRepository.findAll(Sort.by(Sort.Order.asc("name")))).thenReturn(shoppingListEntities);

        when(cocktailService.searchDistinctIngredients(anySet())).thenReturn(mockedCocktailIngredients());
        when(mealService.searchDistinctIngredients(anySet())).thenReturn(mockedMealIngredients());
    }

    @Test
    public void createShoppingList() {
        final ShoppingList shoppingList = shoppingListService.create(mockedShoppingList().getName());
        checkShoppingListEntity(shoppingList);
    }

    @Test
    public void addCocktails() {
        final ShoppingList shoppingList = shoppingListService.addCocktails(UUID.fromString(SHOPPING_LIST_WITH_COCKTAILS), mockedShoppingList().getCocktailIds());
        checkShoppingListEntity(shoppingList);
    }

    @Test
    public void addEmptyCocktailList() {
        final ShoppingList shoppingList = shoppingListService.addCocktails(UUID.fromString(SHOPPING_LIST_NO_COCKTAILS), new HashSet<>());
        checkShoppingListEntityEmptyCocktails(shoppingList);
    }

    @Test(expected = RuntimeException.class)
    public void addCocktailsToNonExistingShoppingList() {
        shoppingListService.addCocktails(UUID.randomUUID(), new HashSet<>());
    }

    @Test
    public void searchShoppingList() {
        final ShoppingList shoppingList = shoppingListService.searchShoppingList(UUID.fromString(SHOPPING_LIST_WITH_COCKTAILS));
        checkShoppingListEntity(shoppingList);
    }

    @Test(expected = RuntimeException.class)
    public void searchNonExistingShoppingList() {
        shoppingListService.searchShoppingList(UUID.randomUUID());
    }

    @Test
    public void searchAllShoppingLists() {
        final List<ShoppingList> shoppingLists = shoppingListService.searchAllShoppingLists();
        // Should always be sorted asc on name
        checkShoppingListEntity(shoppingLists.get(0));
        checkShoppingListEntityEmptyCocktails(shoppingLists.get(1));
    }

    @Test
    public void getDistinctIngredients() {
        final Set<String> ingredients = this.shoppingListService.searchDistinctIngredients(UUID.fromString(SHOPPING_LIST_WITH_COCKTAILS));
        assertEquals(7, ingredients.size());
    }

    private void checkShoppingListEntity(ShoppingList shoppingList) {
        assertNotNull(shoppingList);
        assertEquals(mockedShoppingListEntity().getId(), shoppingList.getShoppingListId());
        assertEquals(mockedShoppingListEntity().getName(), shoppingList.getName());
        checkCocktailEntity(shoppingList.getCocktailIds());
    }

    private void checkShoppingListEntityEmptyCocktails(ShoppingList shoppingList) {
        assertNotNull(shoppingList);
        assertEquals(mockedShoppingListEntityNoCocktails().getId(), shoppingList.getShoppingListId());
        assertEquals(mockedShoppingListEntityNoCocktails().getName(), shoppingList.getName());
        assertEquals(mockedShoppingListEntityNoCocktails().getCocktailEntities().size(), shoppingList.getCocktailIds().size());
    }

    private void checkCocktailEntity(Set<UUID> ids) {
        assertEquals(mockedShoppingListEntity().getCocktailEntities().size(), ids.size());
        mockedShoppingListEntity().getCocktailEntities().stream().map(CocktailEntity::getId).collect(Collectors.toSet())
                .containsAll(ids);
    }

}
