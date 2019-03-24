package a2;
import a2.Controller.OrderMaker;
import a2.Controller.ParlourFileReader;
import a2.Model.Order;
import a2.Model.Pizza;

import java.util.*;

public class PizzaParlour {

    public static void main(String[] args) {
        String filePath = "src/main/Files/";
        // Generates pizzaTypeToRecipe Map
//        Order.pizzaTypeToSizeToPrice = ParlourFileReader.readPizzaTypeSizePrice(filePath + "PizzaTypeSizePrice.csv");
        ParlourFileReader.readPizzaTypeSizePrice(filePath + "PizzaTypeSizePrice.csv");
//        Order.drinkToPrice = ParlourFileReader.readDrinkPrice(filePath + "DrinkPrice.csv");
        ParlourFileReader.readDrinkPrice(filePath + "DrinkPrice.csv");
//        Order.toppingToPrice = ParlourFileReader.readToppingPrice(filePath + "ToppingPrice.csv");
//        Order.allDrinks = Order.drinkToPrice.keySet();
        ParlourFileReader.readToppingPrice(filePath + "ToppingPrice.csv");
//        Pizza.pizzaTypeToRecipe = ParlourFileReader.readPizzaRecipe(filePath + "Recipes.txt");
        ParlourFileReader.readPizzaRecipe(filePath + "Recipes.txt");
//        Pizza.allToppings = Order.toppingToPrice.keySet();

        String startMessage = "If you want to see the menu, please enter Menu.\n"
                + "If you want to make an order, please enter Order.\n"
                + "If you want to exit the program, please enter Exit.";

        System.out.println("Welcome to 301 Pizza!: ");
        System.out.println(startMessage);
        Scanner scanner = new Scanner(System.in);
        String option;

        while (!(option = scanner.nextLine()).equals("Exit")) {
            try {
                if (option.equals("Menu")) {
                    System.out.println("Menu");
                    System.out.println(startMessage);
                } else if (option.equals("Order")) {
                    System.out.println("Order");
                    Order myOrder = new Order();
                    OrderMaker.makeOrder(scanner, myOrder);
                    System.out.println(myOrder);
//                    Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();
//                    builder.setSize("Small");
//                    builder.setType("Pepperoni");
//                    builder.updateToppings("Tomatoes", 3);
//                    builder.updateToppings("Olives", 9);
//                    builder.updateToppings("Tomatoes", 0);
//                    Pizza p = builder.build();
//                    Order order = new Order();
//                    order.addPizza(p);
//                    order.updateDrink("Pepsi", 3);
//                    order.updateDrink("Coke", 4);
//                    order.calculateTotalPrice();
//                    System.out.println(order);
//                    System.out.println("Would you like to pick up, or make a delivery order?: ");

                } else {
                    System.out.println("Invalid Input. Please enter Menu/Order/Exit.");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
        System.exit(0);
    }

}