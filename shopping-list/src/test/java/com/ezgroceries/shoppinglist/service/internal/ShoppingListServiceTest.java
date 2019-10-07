package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.AbstractTest;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.persistence.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.persistence.repositories.ShoppingListRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ShoppingListServiceTest extends AbstractTest {

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @Mock
    private CocktailRepository cocktailRepository;

    private ShoppingListService shoppingListService;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);

        shoppingListService = new ShoppingListServiceImpl(shoppingListRepository, cocktailRepository);

        when(shoppingListRepository.findByName(anyString())).thenReturn(mockedShoppingListEntity());
        when(shoppingListRepository.save(any(ShoppingListEntity.class))).thenReturn(mockedShoppingListEntity());

        when(shoppingListRepository.findById(UUID.fromString("94511c9b-6ee9-491c-acc3-07e45142ca2f")))
                .thenReturn(Optional.of(mockedShoppingListEntity()));
        when(shoppingListRepository.findById(UUID.fromString("ca582a63-bf0e-47a8-a8d6-4aea840a04b0")))
                .thenReturn(Optional.of(mockedShoppingListEntityNoCocktails()));
        when(cocktailRepository.findAllById(mockedShoppingList().getCocktailIds())).thenReturn(
                mockedShoppingListEntity().getCocktailEntities()
        );
    }

    @Test
    public void createShoppingList() {
        final ShoppingList shoppingList = shoppingListService.create(mockedShoppingList());
        assertNotNull(shoppingList);
        checkShoppingListEntity(shoppingList);
    }

    @Test
    public void addCocktails() {
        final ShoppingList shoppingList = shoppingListService.addCocktails(UUID.fromString("94511c9b-6ee9-491c-acc3-07e45142ca2f"), mockedShoppingList().getCocktailIds());
        assertNotNull(shoppingList);
        checkShoppingListEntity(shoppingList);
    }

    @Test
    public void addEmptyCocktailList() {
        final ShoppingList shoppingList = shoppingListService.addCocktails(UUID.fromString("ca582a63-bf0e-47a8-a8d6-4aea840a04b0"), new HashSet<>());
        assertNotNull(shoppingList);
        checkShoppingListEntityEmptyCocktails(shoppingList);
    }

    @Test(expected = RuntimeException.class)
    public void addCocktailsToNonExistingShoppingList() {
        shoppingListService.addCocktails(UUID.randomUUID(), new HashSet<>());
    }

    private void checkShoppingListEntity(ShoppingList shoppingList) {
        assertEquals(mockedShoppingListEntity().getId(), shoppingList.getShoppingListId());
        assertEquals(mockedShoppingListEntity().getName(), shoppingList.getName());
        checkCocktailEntity(shoppingList.getCocktailIds());
    }

    private void checkShoppingListEntityEmptyCocktails(ShoppingList shoppingList) {
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
