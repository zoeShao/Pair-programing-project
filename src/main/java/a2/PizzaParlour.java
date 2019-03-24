package a2;
import a2.Model.Drink;
import a2.Model.Order;
import a2.Model.Pizza;
import com.sun.tools.corba.se.idl.constExpr.Or;

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
        Order.allDrinks = drinks;

        Map<String, Double> sizeToPrice = new HashMap<String, Double>();
        sizeToPrice.put("Small", 3.5);
        sizeToPrice.put("Medium", 4.5);
        sizeToPrice.put("Large", 5.5);
        Map<String, Map<String, Double>> pizzaTypeToSizeToPrice = new HashMap<String, Map<String, Double>>();
        pizzaTypeToSizeToPrice.put("Pepperoni", sizeToPrice);
        Order.pizzaTypeToSizeToPrice = pizzaTypeToSizeToPrice;
        Map<String, Double> toppingToPrice = new HashMap<String, Double>();
        toppingToPrice.put("tomatoes", 1.0);
        toppingToPrice.put("olives", 2.0);
        Order.toppingToPrice = toppingToPrice;
        Map<String, Double> drinkToPrice = new HashMap<String, Double>();
        drinkToPrice.put("coke", 1.0);
        drinkToPrice.put("soda", 2.0);
        Order.drinkToPrice = drinkToPrice;




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
//            Drink d = new Drink("soda");
//            System.out.println(d);
//            Drink dk = new Drink("sod");
//            System.out.println(dk);
            Order order = new Order();
            order.addPizza(p);
            order.updateDrink("soda", 3);
            order.updateDrink("coke", 4);
            order.calculateTotalPrice();
            System.out.println(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        scanner.close();

    }

}