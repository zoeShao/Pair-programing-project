package a2.Controller;

import a2.Model.AllData;
import a2.Model.Order;
import a2.Model.Pizza;

import java.util.List;
import java.util.Scanner;

public class OrderUpdater {
    private static String exitMessage = "If you want to exit the program, please enter Exit.";

    public static void updateOrder(Scanner scanner, Order myOrder) {

        System.out.println("What do you want to update? Please enter Pizza/Drink");
        System.out.println(exitMessage);
        String option = Handler.inputChecker(scanner, 1);
        if (option.equals("Exit")) {
            System.exit(0);
        }
        if (option.equals("Pizza")) {
            updatePizza(scanner, myOrder);
        }
        if (option.equals("Drink")) {
            updateDrink(scanner, myOrder);
        }
    }

    private static Integer pizzaNumberInputChecker(Scanner scanner) {
        String option = scanner.nextLine();
        Integer outputNum = null;
        while (outputNum == null) {
            try {
                Integer pizzaNum = Integer.parseInt(option);
                outputNum = pizzaNum;
            } catch (Exception e) {
                if (option.equals("Add")) {
                    outputNum = 0;
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    option = scanner.nextLine();
                }
            }
        }
        return outputNum;
    }

    private static void updatePizza(Scanner scanner, Order myOrder) {
        System.out.println("Here is all you pizzas:");
        StringBuilder allPizza = new StringBuilder();
        myOrder.allPizzaToString(allPizza);
        System.out.println(allPizza.toString());
        System.out.println("Which pizza do you want to update? Please enter the pizza number(at the front of the sentence).");
        System.out.println("e.g. 1");
        System.out.println("If you want to add a new pizza, please enter Add.");
        System.out.println(exitMessage);
        Integer pizzaNumber = pizzaNumberInputChecker(scanner);
        if (pizzaNumber == 0) {
            OrderMaker.orderPizza(scanner, myOrder);
            System.out.println("If you want to update anything else, please see the instructions below:");
        } else {
            pizzaNumber = pizzaNumber - 1;
            System.out.println("What do you want to update? Please enter Size/Type/Topping.");
            System.out.println("If you want to delete this pizza, please enter Delete.");
            System.out.println("If you finished updating pizza, please enter Confirm.");
            System.out.println(exitMessage);
            String option = Handler.inputChecker(scanner, 11);
            while (!option.equals("Confirm")) {
                if (option.equals("Exit")) {
                    System.exit(0);
                }
                if (option.equals("Delete")) {
                    myOrder.updatePizzaByIndex(pizzaNumber, 0, "", 0);
                    System.out.println("This pizza has been deleted.");
                }
                if (option.equals("Size")) {
                    System.out.println("What size do you want? Please enter "
                            + Handler.convertSetToString(AllData.allSizes) + ".");
                    System.out.println(exitMessage);
                    option = Handler.inputChecker(scanner, 2);
                    myOrder.updatePizzaByIndex(pizzaNumber, 1, option, 0);
                    System.out.println("This pizza size has been updated.");
                }
                if (option.equals("Type")) {
                    System.out.println("What type do you want? Please enter "
                            + Handler.convertSetToString(Pizza.pizzaTypeToRecipe.keySet()) + ".");
                    System.out.println(exitMessage);
                    option = Handler.inputChecker(scanner, 3);
                    myOrder.updatePizzaByIndex(pizzaNumber, 2, option, 0);
                    System.out.println("This pizza type has been updated.");
                }
                if (option.equals("Topping")) {
                    System.out.println("Which topping do you want to update? Please enter "
                            + Handler.convertSetToString(AllData.allToppings) + ".");
                    System.out.println("Please use format: topping:quantity");
                    System.out.println("If you want to delete this topping, please enter topping:0");
                    System.out.println(exitMessage);
                    List<String> toppingToQuantity = Handler.getQuantity(scanner, 1);
                    myOrder.updatePizzaByIndex(pizzaNumber, 3,
                            toppingToQuantity.get(0), Integer.parseInt(toppingToQuantity.get(1)));
                    System.out.println("This topping has been updated.");
                }
                System.out.println("What do else you want to update? Please enter Size/Type/Topping.");
                System.out.println("If you want to delete this pizza, please enter Delete.");
                System.out.println("If you finished updating pizza, please enter Confirm.");
                System.out.println(exitMessage);
                option = Handler.inputChecker(scanner, 11);
            }
            System.out.println("Your pizza has been confirmed. If you want to update anything else, " +
                    "please see the instructions below:");
        }
    }

    private static void updateDrink(Scanner scanner, Order myOrder) {
        System.out.println("Which drink do you want to update? Please enter "
                + Handler.convertSetToString(AllData.allDrinks) + ".");
        System.out.println("Please use format: drink:quantity");
        System.out.println("If you want to delete this drink, please enter drink:0");
        System.out.println(exitMessage);
        List<String> drinkToQuantity = Handler.getQuantity(scanner, 2);
        myOrder.updateDrink(drinkToQuantity.get(0), Integer.parseInt(drinkToQuantity.get(1)));
        System.out.println("This drink has been updated.");
    }
}
