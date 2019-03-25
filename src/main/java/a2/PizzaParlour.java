package a2;

import a2.Controller.*;
import a2.Model.Order;
import a2.Model.Pizza;

import java.util.*;

public class PizzaParlour {
  public static Set<String> allDrinks;
  public static Set<String> allToppings;
  public static Set<String> allSizes;
  public static Set<String> allTypes;

  public static void main(String[] args) {
    String filePath = "src/main/Files/";
    ParlourFileReader.readPizzaTypeSizePrice(filePath + "PizzaTypeSizePrice.csv");
    ParlourFileReader.readDrinkPrice(filePath + "DrinkPrice.csv");
    ParlourFileReader.readToppingPrice(filePath + "ToppingPrice.csv");
    ParlourFileReader.readPizzaRecipe(filePath + "Recipes.txt");

    String orderMessgae = "If you want to make an order, please enter Order.";
    String menuMessage = "If you want to see the menu, please enter Menu.";
    String exitMessage = "If you want to exit the program, please enter Exit.";
    String updateMessage = "If you want to update your order, please enter Update.";
    String cancelMessage = "If you want to cancel your order, please enter Cancel.";
    String confirmMessage =
        "If you want to confirm your order (i.e. you don't want to update anymore),"
            + " please enter Confirm.";
    String deliveryMessage = "If you want to make a delivery, please enter Delivery.";
    String makeDeliveryChoiceMessage =
        "There are three ways to send delivery: in-house, Uber Eats, Foodora,"
            + " which way do you prefer?\n"
            + "Please enter either InHouse or UberEats or Foodora.";
    String pickupMessage = "If you want to pick up your order in store, please enter Pickup.";
    String confirmPickupMessage =
        "[Notice]: Done! The store address is: 40 St.George st.,"
            + " please come to the store to pick up the food.";

    System.out.println("Welcome to 301 Pizza!: ");
    System.out.println(orderMessgae + "\n" + menuMessage + "\n" + exitMessage);
    Scanner scanner = new Scanner(System.in);
    String option = Handler.inputChecker(scanner, 5);
    while (!option.equals("Exit")) {
      try {
        if (option.equals("Menu")) {
          OrderMaker.readMenu(scanner);
        } else if (option.equals("Order")) {
          Order myOrder = new Order();
          OrderMaker.makeOrder(scanner, myOrder);
          if (myOrder.getTotalPrice() == 0.0) {
            System.out.println("You haven't ordered anything. Please try again.");
          } else {
            System.out.println("==========Here is your order==========");
            System.out.println(myOrder);
            System.out.println("[Notice]: Order Submitted Successfully!!!\n");

            System.out.println(updateMessage + "\n" + confirmMessage + "\n" + cancelMessage);
            System.out.println(exitMessage);
            option = Handler.inputChecker(scanner, 10);
            while (option.equals("Update")) {
              OrderUpdater.updateOrder(scanner, myOrder);
              System.out.println("==========Here is your updated order==========");
              System.out.println(myOrder + "\n");
              System.out.println(
                  "If you want to update anything else," + " please see the instructions below:");
              System.out.println(updateMessage + "\n" + confirmMessage + "\n" + cancelMessage);
              System.out.println(exitMessage);
              option = Handler.inputChecker(scanner, 10);
            }
            if (option.equals("Cancel")) {
              System.out.println("[Notice]: Your order has been canceled.");
            } else if (option.equals("Confirm")) {
              if (myOrder.getTotalPrice() == 0.0) {
                System.out.println("You haven't ordered anything. Please try again.");
              } else {
                System.out.println("==========Here is your final order==========");
                System.out.println(myOrder);
                System.out.println("Which way do you like, pickup or delivery?");
                System.out.println("[Notice]: Your order has been confirmed.\n");
                System.out.println(
                    deliveryMessage + "\n" + pickupMessage + "\n" + "\n" + exitMessage);
                option = Handler.inputChecker(scanner, 12);
                if (option.equals("Pickup")) {
                  System.out.println(confirmPickupMessage);
                } else if (option.equals("Delivery")) {
                  System.out.println("Please enter your address:");
                  option = scanner.nextLine();
                  DeliveryMaker.setDeliveryDetails(myOrder, option);
                  System.out.println(makeDeliveryChoiceMessage);
                  option = Handler.inputChecker(scanner, 13);
                  DeliveryMaker.makeDelivery(option);
                }
              }
            }
          }
        }
        System.out.println(
            "\nWelcome to 301 Pizza! Want another order? Please follow the instruction below:");
        System.out.println(orderMessgae + "\n" + menuMessage + "\n" + exitMessage);
        option = Handler.inputChecker(scanner, 5);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
    scanner.close();
    System.exit(0);
  }
}
