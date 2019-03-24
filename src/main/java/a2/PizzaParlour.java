package a2;
import a2.Controller.Handler;
import a2.Controller.OrderMaker;
import a2.Controller.OrderUpdater;
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

        String orderMessgae = "If you want to make an order, please enter Order.";
        String menuMessage = "If you want to see the menu, please enter Menu.";
        String exitMessage = "If you want to exit the program, please enter Exit.";
        String updateMessage = "If you want to update your order, please enter Update.";
        String cancelMessage = "If you want to cancel your order, please enter Cancel.";
        String confirmMessage = "If you want to confirm your order (i.e. you don't want to update anymore), please enter Confirm.";
        String deliveryMessage = "If you want to make a delivery, please enter Delivery.";
        String pickupMessage = "If you want to pick up your order in store, please enter Pickup.";

        System.out.println("Welcome to 301 Pizza!: ");
        System.out.println(orderMessgae);
        System.out.println(menuMessage);
        System.out.println(exitMessage);
        Scanner scanner = new Scanner(System.in);
        String option = Handler.inputChecker(scanner, 5);
        while (!option.equals("Exit")) {
            try {
                if (option.equals("Menu")) {
                    OrderMaker.readMenu(scanner);
                    System.out.println("Menu");
                } else if (option.equals("Order")) {
                    System.out.println("Order");
                    Order myOrder = new Order();
                    OrderMaker.makeOrder(scanner, myOrder);
                    if (myOrder.getTotalPrice() == 0.0) {
                        System.out.println("You haven't ordered anything. Please try again.");
                    } else {
//                        System.out.println("Order Submitted!!!");
//                        System.out.println("[Notice]: Order Submitted!!!");
                        System.out.println("==========Here is your order==========");
                        System.out.println(myOrder);
                        System.out.println("[Notice]: Order Submitted Successfully!!!");

                        System.out.println(updateMessage);
                        System.out.println(confirmMessage);
                        System.out.println(cancelMessage);
                        System.out.println(exitMessage);
                        option = Handler.inputChecker(scanner, 10);
                        while (option.equals("Update")) {
                            OrderUpdater.updateOrder(scanner, myOrder);
                            System.out.println(updateMessage);
                            System.out.println(confirmMessage);
                            System.out.println(cancelMessage);
                            System.out.println(exitMessage);
                            option = Handler.inputChecker(scanner, 10);
                        }
                        if (option.equals("Cancel")) {
                            System.out.println("Your order has been canceled.");
                            System.out.println("If you want to order again, please see instructions below:");
                        } else if (option.equals("Exit")) {
                            System.exit(0);
                        } else if (option.equals("Confirm")) {
                            System.out.println("Your order has been confirmed.");
                            System.out.println(deliveryMessage);
                            System.out.println(pickupMessage);
                            System.out.println(exitMessage);
                        }
                    }
                }
                System.out.println(orderMessgae);
                System.out.println(menuMessage);
                System.out.println(exitMessage);
                option = Handler.inputChecker(scanner, 5);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
        System.exit(0);
    }

}