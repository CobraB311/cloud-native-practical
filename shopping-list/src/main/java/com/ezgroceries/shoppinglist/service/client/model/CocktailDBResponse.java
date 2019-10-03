package com.ezgroceries.shoppinglist.service.client.model;

/*
    Created by Ruben Bernaert (JD68212) on 03/10/2019
*/

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Objects;

public class CocktailDBResponse {

    private List<DrinkResource> drinks;

    public List<DrinkResource> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkResource> drinks) {
        this.drinks = drinks;
    }

    public static class DrinkResource {

        private String idDrink;
        private String strDrink;
        private String strGlass;
        private String strInstructions;
        private String strDrinkThumb;
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;
        private String strIngredient4;
        private String strIngredient5;
        private String strIngredient6;
        private String strIngredient7;

        public String getIdDrink() {
            return idDrink;
        }

        public void setIdDrink(String idDrink) {
            this.idDrink = idDrink;
        }

        public String getStrDrink() {
            return strDrink;
        }

        public void setStrDrink(String strDrink) {
            this.strDrink = strDrink;
        }

        public String getStrGlass() {
            return strGlass;
        }

        public void setStrGlass(String strGlass) {
            this.strGlass = strGlass;
        }

        public String getStrInstructions() {
            return strInstructions;
        }

        public void setStrInstructions(String strInstructions) {
            this.strInstructions = strInstructions;
        }

        public String getStrDrinkThumb() {
            return strDrinkThumb;
        }

        public void setStrDrinkThumb(String strDrinkThumb) {
            this.strDrinkThumb = strDrinkThumb;
        }

        public String getStrIngredient1() {
            return strIngredient1;
        }

        public void setStrIngredient1(String strIngredient1) {
            this.strIngredient1 = strIngredient1;
        }

        public String getStrIngredient2() {
            return strIngredient2;
        }

        public void setStrIngredient2(String strIngredient2) {
            this.strIngredient2 = strIngredient2;
        }

        public String getStrIngredient3() {
            return strIngredient3;
        }

        public void setStrIngredient3(String strIngredient3) {
            this.strIngredient3 = strIngredient3;
        }

        public String getStrIngredient4() {
            return strIngredient4;
        }

        public void setStrIngredient4(String strIngredient4) {
            this.strIngredient4 = strIngredient4;
        }

        public String getStrIngredient5() {
            return strIngredient5;
        }

        public void setStrIngredient5(String strIngredient5) {
            this.strIngredient5 = strIngredient5;
        }

        public String getStrIngredient6() {
            return strIngredient6;
        }

        public void setStrIngredient6(String strIngredient6) {
            this.strIngredient6 = strIngredient6;
        }

        public String getStrIngredient7() {
            return strIngredient7;
        }

        public void setStrIngredient7(String strIngredient7) {
            this.strIngredient7 = strIngredient7;
        }

        @Override
        public int hashCode() {
            return Objects.hash(
                    idDrink, strDrink, strGlass, strInstructions, strDrinkThumb, strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5, strIngredient6, strIngredient7
            );
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            final DrinkResource other = (DrinkResource) obj;
            return Objects.equals(this.idDrink, other.idDrink)
                    && Objects.equals(this.strDrink, other.strDrink)
                    && Objects.equals(this.strGlass, other.strGlass)
                    && Objects.equals(this.strInstructions, other.strInstructions)
                    && Objects.equals(this.strDrinkThumb, other.strDrinkThumb)
                    && Objects.equals(this.strIngredient1, other.strIngredient1)
                    && Objects.equals(this.strIngredient2, other.strIngredient2)
                    && Objects.equals(this.strIngredient3, other.strIngredient3)
                    && Objects.equals(this.strIngredient4, other.strIngredient4)
                    && Objects.equals(this.strIngredient5, other.strIngredient5)
                    && Objects.equals(this.strIngredient6, other.strIngredient6)
                    && Objects.equals(this.strIngredient7, other.strIngredient7);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("idDrink", idDrink)
                    .add("strDrink", strDrink)
                    .add("strGlass", strGlass)
                    .add("strInstructions", strInstructions)
                    .add("strDrinkThumb", strDrinkThumb)
                    .add("strIngredient1", strIngredient1)
                    .add("strIngredient2", strIngredient2)
                    .add("strIngredient3", strIngredient3)
                    .add("strIngredient4", strIngredient4)
                    .add("strIngredient5", strIngredient5)
                    .add("strIngredient6", strIngredient6)
                    .add("strIngredient7", strIngredient7)
                    .toString();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(drinks);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final CocktailDBResponse other = (CocktailDBResponse) obj;
        return Objects.equals(this.drinks, other.drinks);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("drinks", drinks)
                .toString();
    }

}
