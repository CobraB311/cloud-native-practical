package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.service.external.client.model.CocktailDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service // TODO - Write test
public class CocktailServiceImpl implements CocktailService {

    private final CocktailRepository repository;

    @Autowired
    public CocktailServiceImpl(CocktailRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CocktailResource> mergeCocktails(List<CocktailDBResponse.DrinkResource> drinks) {

        if (drinks.isEmpty()) {
            return new ArrayList<>();
        }

        final Set<String> drinkIds = getDrinkIds(drinks);
        final Map<String, CocktailEntity> existingEntityMap = createExistingEntityMap(drinkIds);

        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        final Map<String, CocktailEntity> allDrinks = drinks.stream().map(d -> {
            final CocktailEntity entity = existingEntityMap.get(d.getIdDrink());
            if (entity != null) {
                return entity;
            }
            return createEntity(d);
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allDrinks);
    }

    private Set<String> getDrinkIds(List<CocktailDBResponse.DrinkResource> drinks) {
        if (drinks.isEmpty()) {
            return new HashSet<>();
        }
        return drinks.stream().map(CocktailDBResponse.DrinkResource::getIdDrink).collect(Collectors.toSet());
    }

    private Map<String, CocktailEntity> createExistingEntityMap(Set<String> drinkIds) {
        if (drinkIds.isEmpty()) {
            return new HashMap<>();
        }
        //Get all the ones we already have from our DB, use a Map for convenient lookup
        final List<CocktailEntity> entities = repository.findByIdDrinkIn(drinkIds);
        return entities.stream().collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));
    }

    private CocktailEntity createEntity(CocktailDBResponse.DrinkResource drink) {
        CocktailEntity entity = new CocktailEntity();
        entity.setId(UUID.randomUUID());
        entity.setIdDrink(drink.getIdDrink());
        entity.setName(drink.getStrDrink());
        return entity;
    }

    private CocktailResource createCocktail(CocktailEntity entity, CocktailDBResponse.DrinkResource drink) {
        return new CocktailResource(
                entity.getId(),
                entity.getIdDrink(),
                entity.getName(),
                drink.getStrGlass(),
                drink.getStrInstructions(),
                drink.getStrDrinkThumb(),
                drink.getIngredients()
        );
    }

    private List<CocktailResource> mergeAndTransform(List<CocktailDBResponse.DrinkResource> drinks, Map<String, CocktailEntity> allEntities) {
        return drinks.stream().map(d -> createCocktail(
                allEntities.get(d.getIdDrink()),
                d
        )).collect(Collectors.toList());
    }

}
