package kz.zorg.chef.entity;

import java.util.List;

public class Salad {
    private String name;
    private List<Ingredient> ingredients;

    public Salad(String name, List<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String toString() {
        return getName() + "\n" + getIngredients();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Salad salad = (Salad) o;

        if (name != null ? !name.equals(salad.name) : salad.name != null) return false;
        return ingredients != null ? ingredients.equals(salad.ingredients) : salad.ingredients == null;
    }
}
