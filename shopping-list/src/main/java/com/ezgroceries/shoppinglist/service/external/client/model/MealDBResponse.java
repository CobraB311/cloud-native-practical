package com.ezgroceries.shoppinglist.service.external.client.model;

/*
    Created by Ruben Bernaert (JD68212) on 30/10/2019
*/

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MealDBResponse {

    private List<MealResource> meals;

    public List<MealResource> getMeals() {
        return meals;
    }

    public void setMeals(List<MealResource> meals) {
        this.meals = meals;
    }

    public static class MealResource extends AbstractResource {

        private String idMeal;
        private String strMeal;
        private String strCategory;
        private String strArea;
        private String strInstructions;
        private String strMealThumb;
        private String strTags;
        private String strYoutube;
        private String strIngredient1;
        private String strIngredient2;
        private String strIngredient3;
        private String strIngredient4;
        private String strIngredient5;
        private String strIngredient6;
        private String strIngredient7;
        private String strIngredient8;
        private String strIngredient9;
        private String strIngredient10;
        private String strIngredient11;
        private String strIngredient12;
        private String strIngredient13;
        private String strIngredient14;
        private String strIngredient15;
        private String strIngredient16;
        private String strIngredient17;
        private String strIngredient18;
        private String strIngredient19;
        private String strIngredient20;
        private String strMeasure1; // TODO: Does business needs the measures?
        private String strMeasure2;
        private String strMeasure3;
        private String strMeasure4;
        private String strMeasure5;
        private String strMeasure6;
        private String strMeasure7;
        private String strMeasure8;
        private String strMeasure9;
        private String strMeasure10;
        private String strMeasure11;
        private String strMeasure12;
        private String strMeasure13;
        private String strMeasure14;
        private String strMeasure15;
        private String strMeasure16;
        private String strMeasure17;
        private String strMeasure18;
        private String strMeasure19;
        private String strMeasure20;

        public String getIdMeal() {
            return idMeal;
        }

        public void setIdMeal(String idMeal) {
            this.idMeal = idMeal;
        }

        public String getStrMeal() {
            return strMeal;
        }

        public void setStrMeal(String strMeal) {
            this.strMeal = strMeal;
        }

        public String getStrCategory() {
            return strCategory;
        }

        public void setStrCategory(String strCategory) {
            this.strCategory = strCategory;
        }

        public String getStrArea() {
            return strArea;
        }

        public void setStrArea(String strArea) {
            this.strArea = strArea;
        }

        public String getStrInstructions() {
            return strInstructions;
        }

        public void setStrInstructions(String strInstructions) {
            this.strInstructions = strInstructions;
        }

        public String getStrMealThumb() {
            return strMealThumb;
        }

        public void setStrMealThumb(String strMealThumb) {
            this.strMealThumb = strMealThumb;
        }

        public String getStrTags() {
            return strTags;
        }

        public void setStrTags(String strTags) {
            this.strTags = strTags;
        }

        public String getStrYoutube() {
            return strYoutube;
        }

        public void setStrYoutube(String strYoutube) {
            this.strYoutube = strYoutube;
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

        public String getStrIngredient8() {
            return strIngredient8;
        }

        public void setStrIngredient8(String strIngredient8) {
            this.strIngredient8 = strIngredient8;
        }

        public String getStrIngredient9() {
            return strIngredient9;
        }

        public void setStrIngredient9(String strIngredient9) {
            this.strIngredient9 = strIngredient9;
        }

        public String getStrIngredient10() {
            return strIngredient10;
        }

        public void setStrIngredient10(String strIngredient10) {
            this.strIngredient10 = strIngredient10;
        }

        public String getStrIngredient11() {
            return strIngredient11;
        }

        public void setStrIngredient11(String strIngredient11) {
            this.strIngredient11 = strIngredient11;
        }

        public String getStrIngredient12() {
            return strIngredient12;
        }

        public void setStrIngredient12(String strIngredient12) {
            this.strIngredient12 = strIngredient12;
        }

        public String getStrIngredient13() {
            return strIngredient13;
        }

        public void setStrIngredient13(String strIngredient13) {
            this.strIngredient13 = strIngredient13;
        }

        public String getStrIngredient14() {
            return strIngredient14;
        }

        public void setStrIngredient14(String strIngredient14) {
            this.strIngredient14 = strIngredient14;
        }

        public String getStrIngredient15() {
            return strIngredient15;
        }

        public void setStrIngredient15(String strIngredient15) {
            this.strIngredient15 = strIngredient15;
        }

        public String getStrIngredient16() {
            return strIngredient16;
        }

        public void setStrIngredient16(String strIngredient16) {
            this.strIngredient16 = strIngredient16;
        }

        public String getStrIngredient17() {
            return strIngredient17;
        }

        public void setStrIngredient17(String strIngredient17) {
            this.strIngredient17 = strIngredient17;
        }

        public String getStrIngredient18() {
            return strIngredient18;
        }

        public void setStrIngredient18(String strIngredient18) {
            this.strIngredient18 = strIngredient18;
        }

        public String getStrIngredient19() {
            return strIngredient19;
        }

        public void setStrIngredient19(String strIngredient19) {
            this.strIngredient19 = strIngredient19;
        }

        public String getStrIngredient20() {
            return strIngredient20;
        }

        public void setStrIngredient20(String strIngredient20) {
            this.strIngredient20 = strIngredient20;
        }

        public String getStrMeasure1() {
            return strMeasure1;
        }

        public void setStrMeasure1(String strMeasure1) {
            this.strMeasure1 = strMeasure1;
        }

        public String getStrMeasure2() {
            return strMeasure2;
        }

        public void setStrMeasure2(String strMeasure2) {
            this.strMeasure2 = strMeasure2;
        }

        public String getStrMeasure3() {
            return strMeasure3;
        }

        public void setStrMeasure3(String strMeasure3) {
            this.strMeasure3 = strMeasure3;
        }

        public String getStrMeasure4() {
            return strMeasure4;
        }

        public void setStrMeasure4(String strMeasure4) {
            this.strMeasure4 = strMeasure4;
        }

        public String getStrMeasure5() {
            return strMeasure5;
        }

        public void setStrMeasure5(String strMeasure5) {
            this.strMeasure5 = strMeasure5;
        }

        public String getStrMeasure6() {
            return strMeasure6;
        }

        public void setStrMeasure6(String strMeasure6) {
            this.strMeasure6 = strMeasure6;
        }

        public String getStrMeasure7() {
            return strMeasure7;
        }

        public void setStrMeasure7(String strMeasure7) {
            this.strMeasure7 = strMeasure7;
        }

        public String getStrMeasure8() {
            return strMeasure8;
        }

        public void setStrMeasure8(String strMeasure8) {
            this.strMeasure8 = strMeasure8;
        }

        public String getStrMeasure9() {
            return strMeasure9;
        }

        public void setStrMeasure9(String strMeasure9) {
            this.strMeasure9 = strMeasure9;
        }

        public String getStrMeasure10() {
            return strMeasure10;
        }

        public void setStrMeasure10(String strMeasure10) {
            this.strMeasure10 = strMeasure10;
        }

        public String getStrMeasure11() {
            return strMeasure11;
        }

        public void setStrMeasure11(String strMeasure11) {
            this.strMeasure11 = strMeasure11;
        }

        public String getStrMeasure12() {
            return strMeasure12;
        }

        public void setStrMeasure12(String strMeasure12) {
            this.strMeasure12 = strMeasure12;
        }

        public String getStrMeasure13() {
            return strMeasure13;
        }

        public void setStrMeasure13(String strMeasure13) {
            this.strMeasure13 = strMeasure13;
        }

        public String getStrMeasure14() {
            return strMeasure14;
        }

        public void setStrMeasure14(String strMeasure14) {
            this.strMeasure14 = strMeasure14;
        }

        public String getStrMeasure15() {
            return strMeasure15;
        }

        public void setStrMeasure15(String strMeasure15) {
            this.strMeasure15 = strMeasure15;
        }

        public String getStrMeasure16() {
            return strMeasure16;
        }

        public void setStrMeasure16(String strMeasure16) {
            this.strMeasure16 = strMeasure16;
        }

        public String getStrMeasure17() {
            return strMeasure17;
        }

        public void setStrMeasure17(String strMeasure17) {
            this.strMeasure17 = strMeasure17;
        }

        public String getStrMeasure18() {
            return strMeasure18;
        }

        public void setStrMeasure18(String strMeasure18) {
            this.strMeasure18 = strMeasure18;
        }

        public String getStrMeasure19() {
            return strMeasure19;
        }

        public void setStrMeasure19(String strMeasure19) {
            this.strMeasure19 = strMeasure19;
        }

        public String getStrMeasure20() {
            return strMeasure20;
        }

        public void setStrMeasure20(String strMeasure20) {
            this.strMeasure20 = strMeasure20;
        }

        public Set<String> getTags() {
            Set<String> tagSet = new HashSet<>();
            if (!Strings.isNullOrEmpty(this.getStrTags())) {
                tagSet.addAll(Lists.newArrayList(this.getStrTags().split(",")));
            }
            return tagSet;
        }

        public void setTags(Set<String> tags) {
            if (!tags.isEmpty()) {
                this.setStrTags(String.join(",", tags));
            }
        }

        @Override
        public Set<String> getIngredients() {
            return Stream.of(
                    this.getStrIngredient1(),
                    this.getStrIngredient2(),
                    this.getStrIngredient3(),
                    this.getStrIngredient4(),
                    this.getStrIngredient5(),
                    this.getStrIngredient6(),
                    this.getStrIngredient7(),
                    this.getStrIngredient8(),
                    this.getStrIngredient9(),
                    this.getStrIngredient10(),
                    this.getStrIngredient11(),
                    this.getStrIngredient12(),
                    this.getStrIngredient13(),
                    this.getStrIngredient14(),
                    this.getStrIngredient15(),
                    this.getStrIngredient16(),
                    this.getStrIngredient17(),
                    this.getStrIngredient18(),
                    this.getStrIngredient19(),
                    this.getStrIngredient20()
            )
                    .filter(i -> !Strings.isNullOrEmpty(i))
                    .collect(Collectors.toSet());
        }

        @Override
        public void setIngredients(List<String> ingredients) {
            super.setIngredients(ingredients, 20, this.getClass());
        }

        @Override
        public int hashCode() {
            return Objects.hash(
                    idMeal, strMeal, strCategory, strArea, strInstructions, strMealThumb, strTags, strYoutube,
                    strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5, strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
                    strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15, strIngredient16, strIngredient17, strIngredient18, strIngredient19, strIngredient20,
                    strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5, strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10,
                    strMeasure11, strMeasure12, strMeasure13, strMeasure14, strMeasure15, strMeasure16, strMeasure17, strMeasure18, strMeasure19, strMeasure20
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
            final MealResource other = (MealResource) obj;
            return Objects.equals(this.idMeal, other.idMeal)
                    && Objects.equals(this.strMeal, other.strMeal)
                    && Objects.equals(this.strCategory, other.strCategory)
                    && Objects.equals(this.strArea, other.strArea)
                    && Objects.equals(this.strInstructions, other.strInstructions)
                    && Objects.equals(this.strMealThumb, other.strMealThumb)
                    && Objects.equals(this.strTags, other.strTags)
                    && Objects.equals(this.strYoutube, other.strYoutube)
                    && Objects.equals(this.strIngredient1, other.strIngredient1)
                    && Objects.equals(this.strIngredient2, other.strIngredient2)
                    && Objects.equals(this.strIngredient3, other.strIngredient3)
                    && Objects.equals(this.strIngredient4, other.strIngredient4)
                    && Objects.equals(this.strIngredient5, other.strIngredient5)
                    && Objects.equals(this.strIngredient6, other.strIngredient6)
                    && Objects.equals(this.strIngredient7, other.strIngredient7)
                    && Objects.equals(this.strIngredient8, other.strIngredient8)
                    && Objects.equals(this.strIngredient9, other.strIngredient9)
                    && Objects.equals(this.strIngredient10, other.strIngredient10)
                    && Objects.equals(this.strIngredient11, other.strIngredient11)
                    && Objects.equals(this.strIngredient12, other.strIngredient12)
                    && Objects.equals(this.strIngredient13, other.strIngredient13)
                    && Objects.equals(this.strIngredient14, other.strIngredient14)
                    && Objects.equals(this.strIngredient15, other.strIngredient15)
                    && Objects.equals(this.strIngredient16, other.strIngredient16)
                    && Objects.equals(this.strIngredient17, other.strIngredient17)
                    && Objects.equals(this.strIngredient18, other.strIngredient18)
                    && Objects.equals(this.strIngredient19, other.strIngredient19)
                    && Objects.equals(this.strIngredient20, other.strIngredient20)
                    && Objects.equals(this.strMeasure1, other.strMeasure1)
                    && Objects.equals(this.strMeasure2, other.strMeasure2)
                    && Objects.equals(this.strMeasure3, other.strMeasure3)
                    && Objects.equals(this.strMeasure4, other.strMeasure4)
                    && Objects.equals(this.strMeasure5, other.strMeasure5)
                    && Objects.equals(this.strMeasure6, other.strMeasure6)
                    && Objects.equals(this.strMeasure7, other.strMeasure7)
                    && Objects.equals(this.strMeasure8, other.strMeasure8)
                    && Objects.equals(this.strMeasure9, other.strMeasure9)
                    && Objects.equals(this.strMeasure10, other.strMeasure10)
                    && Objects.equals(this.strMeasure11, other.strMeasure11)
                    && Objects.equals(this.strMeasure12, other.strMeasure12)
                    && Objects.equals(this.strMeasure13, other.strMeasure13)
                    && Objects.equals(this.strMeasure14, other.strMeasure14)
                    && Objects.equals(this.strMeasure15, other.strMeasure15)
                    && Objects.equals(this.strMeasure16, other.strMeasure16)
                    && Objects.equals(this.strMeasure17, other.strMeasure17)
                    && Objects.equals(this.strMeasure18, other.strMeasure18)
                    && Objects.equals(this.strMeasure19, other.strMeasure19)
                    && Objects.equals(this.strMeasure20, other.strMeasure20);
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("idMeal", idMeal)
                    .add("strMeal", strMeal)
                    .add("strCategory", strCategory)
                    .add("strArea", strArea)
                    .add("strInstructions", strInstructions)
                    .add("strMealThumb", strMealThumb)
                    .add("strTags", strTags)
                    .add("strYoutube", strYoutube)
                    .add("strIngredient1", strIngredient1)
                    .add("strIngredient2", strIngredient2)
                    .add("strIngredient3", strIngredient3)
                    .add("strIngredient4", strIngredient4)
                    .add("strIngredient5", strIngredient5)
                    .add("strIngredient6", strIngredient6)
                    .add("strIngredient7", strIngredient7)
                    .add("strIngredient8", strIngredient8)
                    .add("strIngredient9", strIngredient9)
                    .add("strIngredient10", strIngredient10)
                    .add("strIngredient11", strIngredient11)
                    .add("strIngredient12", strIngredient12)
                    .add("strIngredient13", strIngredient13)
                    .add("strIngredient14", strIngredient14)
                    .add("strIngredient15", strIngredient15)
                    .add("strIngredient16", strIngredient16)
                    .add("strIngredient17", strIngredient17)
                    .add("strIngredient18", strIngredient18)
                    .add("strIngredient19", strIngredient19)
                    .add("strIngredient20", strIngredient20)
                    .add("strMeasure1", strMeasure1)
                    .add("strMeasure2", strMeasure2)
                    .add("strMeasure3", strMeasure3)
                    .add("strMeasure4", strMeasure4)
                    .add("strMeasure5", strMeasure5)
                    .add("strMeasure6", strMeasure6)
                    .add("strMeasure7", strMeasure7)
                    .add("strMeasure8", strMeasure8)
                    .add("strMeasure9", strMeasure9)
                    .add("strMeasure10", strMeasure10)
                    .add("strMeasure11", strMeasure11)
                    .add("strMeasure12", strMeasure12)
                    .add("strMeasure13", strMeasure13)
                    .add("strMeasure14", strMeasure14)
                    .add("strMeasure15", strMeasure15)
                    .add("strMeasure16", strMeasure16)
                    .add("strMeasure17", strMeasure17)
                    .add("strMeasure18", strMeasure18)
                    .add("strMeasure19", strMeasure19)
                    .add("strMeasure20", strMeasure20)
                    .toString();
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(meals);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MealDBResponse other = (MealDBResponse) obj;
        return Objects.equals(this.meals, other.meals);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("meals", meals)
                .toString();
    }

}
