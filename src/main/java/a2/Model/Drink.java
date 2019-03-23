package a2.Model;

import java.util.List;

public class Drink {
    private String name;
    public static List<String> allDrinks;

    public Drink(String name) {
        if (!allDrinks.contains(name)) {
            throw new IllegalArgumentException("Invalid drink name: " + name + ". Please try again.");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
