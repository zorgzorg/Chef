package kz.zorg.chef.entity;

public abstract class Ingredient {
    private String name;
    private int caloricValue;
    private int weight;
    private String units;

    public Ingredient() {
    }

    public Ingredient(String name, int caloricValue) {
        this.name = name;
        this.caloricValue = caloricValue;
    }

    public Ingredient(String name, int caloricValue, int weight, String units) {
        this.name = name;
        this.caloricValue = caloricValue;
        this.weight = weight;
        this.units = units;
    }

    public String getName() {
        return name;
    }

    public Integer getCaloricValue() {
        return caloricValue;
    }

    public int getWeight() {
        return weight;
    }

    public String getUnits() {
        return units;
    }

    public String toString() {
        return getName() + " - " + getWeight() + " " + getUnits() + " Калорийность в 100 г: " + getCaloricValue() + "\n";
    }
}
