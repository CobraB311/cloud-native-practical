package com.ezgroceries.shoppinglist.persistence.repositories;

/*
    Created by Ruben Bernaert (JD68212) on 03/10/2019
*/

import com.ezgroceries.shoppinglist.persistence.entities.CocktailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CocktailRepository extends JpaRepository<CocktailEntity, UUID> {

    List<CocktailEntity> findByIdDrinkIn(Set<String> ids);

}
