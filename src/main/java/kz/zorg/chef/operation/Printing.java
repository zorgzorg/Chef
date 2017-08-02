package kz.zorg.chef.operation;

import kz.zorg.chef.entity.Ingredient;
import kz.zorg.chef.runner.Runner;

import java.util.List;

/**
 * Created by Finn on 21.06.2017.
 */
public class Printing {
    //Вывод в консоль перечня ингредиентов блюда
    public static void print(List<Ingredient> products, Runner.printOption option){
        String additionText = "";
        for(Ingredient p: products){
            switch (option){
                case LIST:
                    additionText = " - " + p.getWeight() + " " + p.getUnits();
                    break;
                case FILTER:
                    additionText = " - " + p.getCaloricValue() + " Ккал";
                    break;
                default:
                    break;
            }
            System.out.println(p.getName() + additionText);
        }
    }
}
