package a2;
import a2.Model.Drink;
import a2.Model.Pizza;

import java.util.*;

public class PizzaParlour {

    public static void main(String[] args) {

        List allToppings = new ArrayList<String>();
        allToppings.add("tomatoes");
        allToppings.add("olives");
        Pizza.allToppings = allToppings;
        Map<String, String> allRecipes = new HashMap<String, String>();
        allRecipes.put("Pepperoni", "Hi");
        allRecipes.put("Margherita", "Oh");
        Pizza.pizzaTypeToRecipe = allRecipes;
        List drinks = new ArrayList<String>();
        drinks.add("coke");
        drinks.add("soda");
        Drink.allDrinks = drinks;

        System.out.println("Welcome to 301 Pizza!: ");
        System.out.println("Would you like to order in, or make a delivery order?: ");
        Scanner scanner = new Scanner(System.in);
        String orderType = scanner.nextLine();
        try {
            Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();
            builder.setSize("Small");
            builder.setType("Pepperoni");
            builder.updateToppings("tomatoes", 3);
            builder.updateToppings("olives", 9);
            builder.updateToppings("tomatoes", 0);
            Pizza p = builder.build();
            System.out.println(p.getRecipe());
            System.out.println(p);
            Drink d = new Drink("soda");
            System.out.println(d);
            Drink dk = new Drink("sod");
            System.out.println(dk);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        scanner.close();

    }

}