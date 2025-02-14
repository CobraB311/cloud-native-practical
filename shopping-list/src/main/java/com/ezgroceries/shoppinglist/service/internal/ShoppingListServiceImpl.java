package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.entities.MealEntity;
import com.ezgroceries.shoppinglist.persistence.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.persistence.repositories.ShoppingListRepository;
import com.ezgroceries.shoppinglist.security.user.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailService cocktailService;
    private final MealService mealService;
    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository, CocktailService cocktailService, MealService mealService, AuthenticationFacade authenticationFacade) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailService = cocktailService;
        this.mealService = mealService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public ShoppingList create(@Nonnull String name) {
        final ShoppingListEntity entity = shoppingListRepository.findByName(name);
        if (entity != null) {
            return createShoppingList(entity);
        }
        final ShoppingList shoppingList = new ShoppingList(
                UUID.randomUUID(), name, new HashSet<>(), new HashSet<>()
        );
        final ShoppingListEntity newEntity = shoppingListRepository.save(createEntity(shoppingList));
        shoppingListRepository.flush();
        return createShoppingList(newEntity);
    }

    @Override
    public ShoppingList addCocktails(@Nonnull UUID shoppingListId, Set<UUID> cocktails) {
        final Optional<ShoppingListEntity> optionalEntity = this.shoppingListRepository.findById(shoppingListId);
        return optionalEntity.map(e -> {
            if (cocktails.isEmpty()) {
                return createShoppingList(e);
            }
            e.setCocktailEntities(
                    cocktailService.createEntities(cocktailService.findCocktails(cocktails))
            );
            final ShoppingListEntity newEntity = this.shoppingListRepository.save(e);
            this.shoppingListRepository.flush();
            return createShoppingList(newEntity);
        }).orElseThrow(() -> new RuntimeException("No shopping list found for: '" + shoppingListId + "'. You first need to create one."));
    }

    @Override
    public ShoppingList addMeals(@Nonnull UUID shoppingListId, Set<UUID> meals) {
        final Optional<ShoppingListEntity> optionalEntity = this.shoppingListRepository.findById(shoppingListId);
        return optionalEntity.map(e -> {
            if (meals.isEmpty()) {
                return createShoppingList(e);
            }
            e.setMealEntities(
                    mealService.createEntities(mealService.findMeals(meals))
            );
            final ShoppingListEntity newEntity = this.shoppingListRepository.save(e);
            this.shoppingListRepository.flush();
            return createShoppingList(newEntity);
        }).orElseThrow(() -> new RuntimeException("No shopping list found for: '" + shoppingListId + "'. You first need to create one."));
    }

    @Override
    public ShoppingList searchShoppingList(@Nonnull UUID id) {
        // TODO: Add shopping lists in ehcache
        final ShoppingListEntity entity = this.shoppingListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find shopping list with id: '" + id + "'."));
        return createShoppingList(entity);
    }

    @Override
    public Set<String> searchDistinctIngredients(@Nonnull UUID shoppingListId) {
        final ShoppingList shoppingList = searchShoppingList(shoppingListId);
        final List<String> cocktailIngredients = this.cocktailService.searchDistinctIngredients(shoppingList.getCocktailIds());
        final List<String> mealIngredients = this.mealService.searchDistinctIngredients(shoppingList.getMealIds());
        return Stream.of(cocktailIngredients, mealIngredients).flatMap(List::stream).collect(Collectors.toSet());
    }

    @Override
    public List<ShoppingList> searchAllShoppingLists() {
        final List<ShoppingListEntity> entities = this.shoppingListRepository.findAll(Sort.by(Sort.Order.asc("name")));
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }
        // Make sure only the shopping lists of the user are returned
        return entities.stream().filter(e -> authenticationFacade.getUserName().equals(e.getUserId()))
                .map(this::createShoppingList).collect(Collectors.toList());
    }

    private ShoppingListEntity createEntity(ShoppingList shoppingList) {
        ShoppingListEntity entity = new ShoppingListEntity();
        entity.setId(shoppingList.getShoppingListId());
        entity.setName(shoppingList.getName());
        entity.setUserId(authenticationFacade.getUserName());
        return entity;
    }

    private ShoppingList createShoppingList(ShoppingListEntity entity) {
        return new ShoppingList(
                entity.getId(),
                entity.getName(),
                entity.getUserId(),
                entity.getCocktailEntities().stream().map(CocktailEntity::getId).collect(Collectors.toSet()),
                entity.getMealEntities().stream().map(MealEntity::getId).collect(Collectors.toSet())
        );
    }

}
