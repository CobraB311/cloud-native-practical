package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
*/

import com.ezgroceries.shoppinglist.model.MealResource;
import com.ezgroceries.shoppinglist.persistence.entities.MealEntity;
import com.ezgroceries.shoppinglist.persistence.repositories.MealRepository;
import com.ezgroceries.shoppinglist.service.external.MealExtService;
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

// TODO: Refactor - Has similar code as Cocktail service

@Service
public class MealServiceImpl implements MealService {

    private final MealExtService extService;
    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealExtService extService, MealRepository repository) {
        this.extService = extService;
        this.repository = repository;
    }

    @Override
    public List<MealResource> searchMeals(@Nonnull String search) {
        if (Strings.isNullOrEmpty(search)) {
            return new ArrayList<>();
        }
        final List<MealResource> mealResources = this.extService.searchMeals(search);
        return mergeMeals(mealResources);
    }

    @Override
    public List<MealResource> findMeals(Set<UUID> mealIds) {
        if (mealIds.isEmpty()) {
            return new ArrayList<>();
        }
        return createMeals(this.repository.findAllById(mealIds));
    }

    // Need to expose a create cocktail entity method for shopping list service.
    // We don't won't to expose the real entities and don't wont to use repositories in another service
    @Override
    public List<MealEntity> createEntities(List<MealResource> meals) {
        if (meals.isEmpty()) {
            return new ArrayList<>();
        }
        return meals.stream().map(this::createEntity).collect(Collectors.toList());
    }

    private List<MealResource> mergeMeals(List<MealResource> meals) {

        if (meals.isEmpty()) {
            return new ArrayList<>();
        }

        final Set<String> mealIds = getMealIds(meals);
        final Map<String, MealEntity> existingEntityMap = createExistingEntityMap(mealIds);

        final List<MealEntity> allMeals = meals.stream().map(m -> {
            final MealEntity entity = existingEntityMap.get(m.getMealId());
            if (entity != null) {
                return entity;
            }
            final MealEntity newEntity = createEntity(m);
            repository.save(newEntity);
            repository.flush();
            return newEntity;
        }).collect(Collectors.toList());

        return allMeals.stream().map(this::createMeal).collect(Collectors.toList());
    }

    private Set<String> getMealIds(List<MealResource> meals) {
        if (meals.isEmpty()) {
            return new HashSet<>();
        }
        return meals.stream().map(MealResource::getMealId).collect(Collectors.toSet());
    }

    private Map<String, MealEntity> createExistingEntityMap(Set<String> mealIds) {
        if (mealIds.isEmpty()) {
            return new HashMap<>();
        }
        final List<MealEntity> entities = repository.findByIdMealIn(mealIds);
        return entities.stream().collect(Collectors.toMap(MealEntity::getIdMeal, o -> o, (o, o2) -> o));
    }

    private MealEntity createEntity(MealResource meal) {
        MealEntity entity = new MealEntity();
        entity.setId(UUID.randomUUID());
        entity.setIdMeal(meal.getMealId());
        entity.setName(meal.getName());
        entity.setCategory(meal.getCategory());
        entity.setArea(meal.getArea());
        entity.setInstructions(meal.getInstructions());
        entity.setImageLink(meal.getImage());
        entity.setYoutubeLink(meal.getYoutube());
        entity.setTags(meal.getTags());
        entity.setIngredients(meal.getIngredients());
        return entity;
    }

    private MealResource createMeal(MealEntity entity) {
        return new MealResource(
                entity.getId(),
                entity.getIdMeal(),
                entity.getName(),
                entity.getCategory(),
                entity.getArea(),
                entity.getInstructions(),
                entity.getImageLink(),
                entity.getYoutubeLink(),
                entity.getTags(),
                entity.getIngredients()
        );
    }

    private List<MealResource> createMeals(List<MealEntity> allEntities) {
        return allEntities.stream().map(this::createMeal).collect(Collectors.toList());
    }

}

