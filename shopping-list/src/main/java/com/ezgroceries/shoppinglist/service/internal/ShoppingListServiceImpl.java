package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.persistence.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.persistence.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailRepository cocktailRepository;

    @Autowired
    public ShoppingListServiceImpl(ShoppingListRepository shoppingListRepository, CocktailRepository cocktailRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailRepository = cocktailRepository;
    }

    @Override
    public ShoppingList create(@Nonnull ShoppingList shoppingList) {
        final ShoppingListEntity entity = shoppingListRepository.findByName(shoppingList.getName());
        if (entity != null) {
            return createShoppingList(entity);
        }
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
                    findCocktails(cocktails)
            );
            final ShoppingListEntity newEntity = this.shoppingListRepository.save(e);
            this.shoppingListRepository.flush();
            return createShoppingList(newEntity);
        }).orElseThrow(() -> new RuntimeException("No shopping list found for: '" + shoppingListId + "'. You first need to create one."));
    }

    @Override
    public ShoppingList searchShoppingList(@Nonnull UUID id) {
        final ShoppingListEntity entity = this.shoppingListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find shopping list with id: '" + id + "'."));
        return createShoppingList(entity);
    }

    private ShoppingListEntity createEntity(ShoppingList shoppingList) {
        ShoppingListEntity entity = new ShoppingListEntity();
        entity.setId(shoppingList.getShoppingListId());
        entity.setName(shoppingList.getName());
        return entity;
    }

    private ShoppingList createShoppingList(ShoppingListEntity entity) {
        return new ShoppingList(
                entity.getId(),
                entity.getName(),
                entity.getCocktailEntities().stream().map(CocktailEntity::getId).collect(Collectors.toSet())
        );
    }

    private List<CocktailEntity> findCocktails(Set<UUID> cocktailsIds) {
        if (cocktailsIds.isEmpty()) {
            return new ArrayList<>();
        }
        return this.cocktailRepository.findAllById(cocktailsIds);
    }

}
