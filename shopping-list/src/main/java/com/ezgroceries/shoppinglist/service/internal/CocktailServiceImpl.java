package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.service.external.CocktailExtService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
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
    private final CocktailExtService extService;

    @Autowired
    public CocktailServiceImpl(CocktailRepository repository, CocktailExtService extService) {
        this.repository = repository;
        this.extService = extService;
    }

    @Override
    public List<CocktailResource> searchCocktails(@Nonnull String search) {
        if (Strings.isNullOrEmpty(search)) {
            return new ArrayList<>();
        }
        final List<CocktailResource> cocktailResources = this.extService.searchCocktails(search);
        return mergeCocktails(cocktailResources);
    }

    private List<CocktailResource> mergeCocktails(List<CocktailResource> drinks) {

        if (drinks.isEmpty()) {
            return new ArrayList<>();
        }

        final Set<String> drinkIds = getDrinkIds(drinks);
        final Map<String, CocktailEntity> existingEntityMap = createExistingEntityMap(drinkIds);

        //Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        final Map<String, CocktailEntity> allDrinks = drinks.stream().map(d -> {
            final CocktailEntity entity = existingEntityMap.get(d.getDrinkId());
            if (entity != null) {
                return entity;
            }
            final CocktailEntity newEntity = createEntity(d);
            repository.save(newEntity);
            repository.flush();
            return newEntity;
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));

        //Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allDrinks);
    }

    private Set<String> getDrinkIds(List<CocktailResource> drinks) {
        if (drinks.isEmpty()) {
            return new HashSet<>();
        }
        return drinks.stream().map(CocktailResource::getDrinkId).collect(Collectors.toSet());
    }

    private Map<String, CocktailEntity> createExistingEntityMap(Set<String> drinkIds) {
        if (drinkIds.isEmpty()) {
            return new HashMap<>();
        }
        //Get all the ones we already have from our DB, use a Map for convenient lookup
        final List<CocktailEntity> entities = repository.findByIdDrinkIn(drinkIds);
        return entities.stream().collect(Collectors.toMap(CocktailEntity::getIdDrink, o -> o, (o, o2) -> o));
    }

    private CocktailEntity createEntity(CocktailResource drink) {
        CocktailEntity entity = new CocktailEntity();
        entity.setId(UUID.randomUUID());
        entity.setIdDrink(drink.getDrinkId());
        entity.setName(drink.getName());
        return entity;
    }

    private CocktailResource createCocktail(CocktailEntity entity, CocktailResource drink) {
        return new CocktailResource(
                entity.getId(),
                entity.getIdDrink(),
                entity.getName(),
                drink.getGlass(),
                drink.getInstructions(),
                drink.getImage(),
                drink.getIngredients()
        );
    }

    private List<CocktailResource> mergeAndTransform(List<CocktailResource> drinks, Map<String, CocktailEntity> allEntities) {
        return drinks.stream().map(d -> createCocktail(
                allEntities.get(d.getDrinkId()),
                d
        )).collect(Collectors.toList());
    }

}
