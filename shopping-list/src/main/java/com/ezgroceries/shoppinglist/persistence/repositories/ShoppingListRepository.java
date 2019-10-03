package com.ezgroceries.shoppinglist.persistence.repositories;

/*
    Created by Ruben Bernaert (JD68212) on 03/10/2019
*/

import com.ezgroceries.shoppinglist.persistence.entities.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, UUID> {



}
