package kz.zorg.chef.operation;

import kz.zorg.chef.entity.Ingredient;
import kz.zorg.chef.entity.Salad;

import java.util.ArrayList;
import java.util.List;

public class Operations {

    //Расчет калорийности блюда
    public static int calculateCaloricValue(Salad salad){
        List<Ingredient> products = salad.getIngredients();
        int calValue = 0;
        int weight = 0;

        for(Ingredient p: products){
            calValue += (p.getCaloricValue()*p.getWeight());
            weight += p.getWeight();
        }

        if(weight != 0) {
            return calValue / weight;
        } else {
            return 0;
        }
    }

    //Фильтрация ингредиентов блюда в заданном диапазоне калорийности
    public static List<Ingredient> filter(List<Ingredient> products, int min, int max){
        List<Ingredient> result = new ArrayList<Ingredient>();
        for(Ingredient p: products){
            if(p.getCaloricValue() >= min && p.getCaloricValue() <= max )
                result.add(p);
        }
        return result;
    }
}
