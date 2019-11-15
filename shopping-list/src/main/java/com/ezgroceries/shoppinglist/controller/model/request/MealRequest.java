package com.ezgroceries.shoppinglist.controller.model.request;

/*
    Created by Ruben Bernaert (JD68212) on 04/10/2019
*/

import com.google.common.base.MoreObjects;

import java.util.Objects;
import java.util.UUID;

public class MealRequest {

    private UUID mealId;

    public UUID getMealId() {
        return mealId;
    }

    public void setMealId(UUID mealId) {
        this.mealId = mealId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MealRequest other = (MealRequest) obj;
        return Objects.equals(this.mealId, other.mealId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("mealId", mealId)
                .toString();
    }

}
