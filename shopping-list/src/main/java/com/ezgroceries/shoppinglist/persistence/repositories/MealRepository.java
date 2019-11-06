package com.ezgroceries.shoppinglist.persistence.repositories;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
*/

import com.ezgroceries.shoppinglist.persistence.entities.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface MealRepository extends JpaRepository<MealEntity, UUID> {

    List<MealEntity> findByIdMealIn(Set<String> ids);

    List<MealEntity> findByNameContainingIgnoreCase(String name);

}
