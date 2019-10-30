package com.ezgroceries.shoppinglist.service.internal;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
*/

import com.ezgroceries.shoppinglist.model.MealResource;
import com.ezgroceries.shoppinglist.service.external.MealExtService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    private final MealExtService extService;

    @Autowired
    public MealServiceImpl(MealExtService extService) {
        this.extService = extService;
    }

    @Override
    public List<MealResource> searchMeals(@Nonnull String search) {
        if (Strings.isNullOrEmpty(search)) {
            return new ArrayList<>();
        }
        return this.extService.searchMeals(search);
    }
}

