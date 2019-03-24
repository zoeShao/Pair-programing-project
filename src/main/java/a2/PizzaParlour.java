package a2;
import a2.Controller.ParlourFileReader;
import a2.Model.Order;
import a2.Model.Pizza;

import java.util.*;

public class PizzaParlour {

    public static void main(String[] args) {
        String filePath = "src/main/Files/";
        // Generates pizzaTypeToRecipe Map
        Order.pizzaTypeToSizeToPrice = ParlourFileReader.readPizzaTypeSizePrice(filePath + "PizzaTypeSizePrice.csv");
        Order.drinkToPrice = ParlourFileReader.readDrinkPrice(filePath + "DrinkPrice.csv");
        Order.toppingToPrice = ParlourFileReader.readToppingPrice(filePath + "ToppingPrice.csv");
        Order.allDrinks = Order.drinkToPrice.keySet();
        Pizza.pizzaTypeToRecipe = ParlourFileReader.readPizzaRecipe(filePath + "Recipes.txt");
        Pizza.allToppings = Order.toppingToPrice.keySet();

        System.out.println("Welcome to 301 Pizza!: ");
        System.out.println("Would you like to order in, or make a delivery order?: ");
        Scanner scanner = new Scanner(System.in);
        String orderType = scanner.nextLine();
        try {
            Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();
            builder.setSize("Small");
            builder.setType("Pepperoni");
            builder.updateToppings("Tomatoes", 3);
            builder.updateToppings("Olives", 9);
            builder.updateToppings("Tomatoes", 0);
            Pizza p = builder.build();
            Order order = new Order();
            order.addPizza(p);
            order.updateDrink("Pepsi", 3);
            order.updateDrink("Coke", 4);
            order.calculateTotalPrice();
            System.out.println(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        scanner.close();

    }

}