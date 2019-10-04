package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.persistence.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.persistence.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private final ShoppingListRepository repository;

    @Autowired
    public ShoppingListServiceImpl(ShoppingListRepository repository) {
        this.repository = repository;
    }

    @Override
    public ShoppingList create(ShoppingList shoppingList) {
        final ShoppingListEntity entity = repository.findByName(shoppingList.getName());
        if (entity != null) {
            return createShoppingList(entity);
        }
        final ShoppingListEntity newEntity = repository.save(createEntity(shoppingList));
        repository.flush();
        return createShoppingList(newEntity);
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
                entity.getName()
        );
    }

}
