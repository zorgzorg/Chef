package kz.zorg.chef.operation;

import kz.zorg.chef.entity.Ingredient;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {
    //Сортировка ингредиентов блюда по наименованию в алфавитном порядке
    public static List<Ingredient> sort(List<Ingredient> products){
        Collections.sort(products, new Comparator<Ingredient>() {
            public int compare(Ingredient p1, Ingredient p2) {
                return p1.toString().compareTo(p2.toString());
            }
        });
        return products;
    }
}
