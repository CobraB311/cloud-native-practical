package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.ezgroceries.shoppinglist.model.CocktailResource;
import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.repositories.CocktailRepository;
import com.ezgroceries.shoppinglist.service.external.CocktailExtService;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
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

@Service
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

    @Override
    public List<String> searchDistinctIngredients(Set<UUID> cocktailIds) {
        if (cocktailIds.isEmpty()) {
            return new ArrayList<>();
        }
        final List<CocktailEntity> entities = this.repository.findAllById(cocktailIds);
        if (entities.isEmpty()) {
            return new ArrayList<>();
        }
        return entities.stream().flatMap(set -> set.getIngredients().stream()).distinct().collect(Collectors.toList());
    }

    @Override
    public List<CocktailResource> findCocktails(Set<UUID> cocktailsIds) {
        if (cocktailsIds.isEmpty()) {
            return new ArrayList<>();
        }
        return createCocktails(this.repository.findAllById(cocktailsIds));
    }

    // Need to expose a create cocktail entity method for shopping list service.
    // We don't won't to expose the real entities and don't wont to use repositories in another service
    @Override
    public List<CocktailEntity> createEntities(List<CocktailResource> cocktails) {
        if (cocktails.isEmpty()) {
            return new ArrayList<>();
        }
        return cocktails.stream().map(this::createEntity).collect(Collectors.toList());
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
        return createCocktails(Lists.newArrayList(allDrinks.values()));
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
        entity.setIngredients(drink.getIngredients());
        entity.setGlass(drink.getGlass());
        entity.setInstructions(drink.getInstructions());
        entity.setImageLink(drink.getImage());
        return entity;
    }

    private CocktailResource createCocktail(CocktailEntity entity) {
        return new CocktailResource(
                entity.getId(),
                entity.getIdDrink(),
                entity.getName(),
                entity.getGlass(),
                entity.getInstructions(),
                entity.getImageLink(),
                entity.getIngredients()
        );
    }

    private List<CocktailResource> createCocktails(List<CocktailEntity> allEntities) {
        return allEntities.stream().map(this::createCocktail).collect(Collectors.toList());
    }

}
