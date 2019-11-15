package com.ezgroceries.shoppinglist.controller.model.response;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class MealIdResponse {

    private String mealId;

    public MealIdResponse(String mealId) {
        this.mealId = mealId;
    }

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
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
        final MealIdResponse other = (MealIdResponse) obj;
        return Objects.equals(this.mealId, other.mealId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("mealId", mealId)
                .toString();
    }

}
