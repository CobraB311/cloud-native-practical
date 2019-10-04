package com.ezgroceries.shoppinglist.model.request;

/*
    Created by Ruben Bernaert (JD68212) on 30/09/2019
*/

import java.util.Objects;
import java.util.StringJoiner;

public class ShoppingListRequest {

    private String name;

    public ShoppingListRequest() {
    }

    public ShoppingListRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final ShoppingListRequest other = (ShoppingListRequest) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ShoppingListRequest.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }

}
