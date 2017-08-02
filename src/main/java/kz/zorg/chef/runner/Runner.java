package kz.zorg.chef.runner;


import kz.zorg.chef.entity.Salad;
import kz.zorg.chef.operation.Operations;
import kz.zorg.chef.operation.Printing;
import kz.zorg.chef.operation.ReadXMLFile;
import kz.zorg.chef.operation.Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;


public class Runner {

    public static enum printOption {
        LIST(0),
        SORT(1),
        FILTER(2);

        printOption(int i) { }
    }

    private final static String APPLICATION_NAME = "Шеф-повар";
    private final static String INGREDIENTS = "Ингредиенты";
    private final static String CALORIC_VALUE_DISH = "Калорийность блюда";
    private final static String SORTING_VEGETABLES = "Сортировка овощей из салата по наименованию";
    private final static String INDICATE_CALORIC_RANGE = "Укажите диапазон калорийности";
    private final static String LOWER_BOUNDARY = "Нижняя граница диапазона калорийности";
    private final static String UPPER_BOUNDARY = "Верхняя граница диапазона калорийности";
    private final static String VEGETABLES_WITHIN_CALORIC_RANGE = "Овощи, соответствующие диапазону калорийности";
    private final static String KILOCALORIES = "Ккал";
    private final static String ERROR_NUMBER_MUST_BE_BIGGER_OR_EQUAL_ZERO = "Ошибка ввода. Число должно быть больше или равно нулю.";
    private final static String ERROR_INDICATE_NUMBER = "Ошибка ввода. Укажите число.";
    private final static String IOEXCEPTION = "IOException: ";

    public static void main(String[] args) {
        int min;
        int max;
        Random random = new Random();
        final int RECIPE_LIMIT = 3;

        System.out.println(APPLICATION_NAME);

        //метод makeSalad(int id) создает салат на основе данных из файла salads.xml с использованием случайного число
        //для выбора рецепта
        Salad salad = ReadXMLFile.makeSalad(random.nextInt(RECIPE_LIMIT));

        //метод getName() выводит название салата
        System.out.println(salad.getName()+"\n");

        System.out.println(INGREDIENTS);
        //метод print(ArrayList<entity> products) выводит список ингредиентов салата
        Printing.print(salad.getIngredients(), printOption.LIST);
        System.out.println();

        //метод getCaloricValue(Dish dish) рассчитывае и выводит общую калорийность салата
        System.out.println(CALORIC_VALUE_DISH + " - " + Operations.calculateCaloricValue(salad)+"\n");

        //метод sort(ArrayList<entity> products) сортирует ингредиенты салата по алфавиту
        ;System.out.println(SORTING_VEGETABLES);
        Printing.print(Sorting.sort(salad.getIngredients()), printOption.SORT);
        System.out.println();

        System.out.println(INDICATE_CALORIC_RANGE);
        min = getUserInput(LOWER_BOUNDARY);
        max = getUserInput(UPPER_BOUNDARY);

        int[] range = checkMinMaxRange(min, max);

        System.out.println(VEGETABLES_WITHIN_CALORIC_RANGE + " " + range[0] +" - " + range[1] + " " + KILOCALORIES );

        //метод filter(ArrayList<entity> products), int min, int max) фильтрует ингредиенты согласно заданному
        //диапазону калорийности (min и  max)
        Printing.print(Operations.filter(salad.getIngredients(), min, max), printOption.FILTER);
    }

    private static int getUserInput(String prompt){
        int number = 0;
        boolean isNumber = false;

        System.out.print(prompt + " ");

        while(!isNumber) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                number = Integer.parseInt(reader.readLine());
                if(number >= 0) {
                    isNumber = true;
                } else {
                    System.out.println(ERROR_NUMBER_MUST_BE_BIGGER_OR_EQUAL_ZERO);
                }
            } catch (NumberFormatException e) {
                System.out.println(ERROR_INDICATE_NUMBER);
            } catch (IOException e) {
                System.out.println(IOEXCEPTION + e);
            }
        }
        return number;
    }

    private static int[] checkMinMaxRange(int min, int max){
        int tempValue;

        if(min > max ) {
            tempValue = max;
            max = min;
            min = tempValue;
        }

        return new int[]{min, max};
    }
}
