package a2.Controller;

import a2.Model.Order;
import a2.PizzaParlour;

import java.util.List;
import java.util.Scanner;

public class OrderUpdater {
    private static String exitMessage = "If you want to exit the program, please enter Exit.";
    private static String pizzaUpdateMessage = "What do you want to update? Please enter Size/Type/Topping.\n"
            + "If you want to delete this pizza, please enter Delete.\n"
            + "If you finished updating pizza, please enter Confirm.";

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

    private static void updatePizza(Scanner scanner, Order myOrder) {
        System.out.println("Here is all your pizzas:");
        StringBuilder allPizza = new StringBuilder();
        myOrder.allPizzaToString(allPizza);
        System.out.println(allPizza.toString());
        System.out.println("Which pizza do you want to update? " +
                "Please enter the pizza number(at the front of the sentence).");
        System.out.println("e.g. 1");
        System.out.println("If you want to add a new pizza, please enter Add.");
        System.out.println(exitMessage);
        Integer pizzaNumber = pizzaNumberInputChecker(scanner, myOrder);
        if (pizzaNumber == 0) {
            OrderMaker.orderPizza(scanner, myOrder);
        } else {
            pizzaNumber = pizzaNumber - 1;
            System.out.println(pizzaUpdateMessage);
            System.out.println(exitMessage);
            String option = Handler.inputChecker(scanner, 11);
            while (!option.equals("Confirm")) {
                if (option.equals("Delete")) {
                    myOrder.updatePizzaByIndex(pizzaNumber, 0, "", 0);
                    System.out.println("This pizza has been deleted.");
                }
                if (option.equals("Size")) {
                    System.out.println("What size do you want? Please enter "
                            + Handler.convertSetToString(PizzaParlour.allSizes) + ".");
                    System.out.println(exitMessage);
                    option = Handler.inputChecker(scanner, 2);
                    myOrder.updatePizzaByIndex(pizzaNumber, 1, option, 0);
                    System.out.println("This pizza size has been updated.");
                }
                if (option.equals("Type")) {
                    System.out.println("What type do you want? Please enter "
                            + Handler.convertSetToString(PizzaParlour.allTypes) + ".");
                    System.out.println(exitMessage);
                    option = Handler.inputChecker(scanner, 3);
                    myOrder.updatePizzaByIndex(pizzaNumber, 2, option, 0);
                    System.out.println("This pizza type has been updated.");
                }
                if (option.equals("Topping")) {
                    System.out.println("Which topping do you want to update? Please enter "
                            + Handler.convertSetToString(PizzaParlour.allToppings) + ".");
                    System.out.println("Please use format: topping:quantity");
                    System.out.println("If you want to delete this topping, please enter topping:0");
                    System.out.println(exitMessage);
                    List<String> toppingToQuantity = Handler.getQuantity(scanner, 1);
                    myOrder.updatePizzaByIndex(pizzaNumber, 3,
                            toppingToQuantity.get(0), Integer.parseInt(toppingToQuantity.get(1)));
                    System.out.println("This topping has been updated.");
                }
                System.out.println(pizzaUpdateMessage);
                System.out.println(exitMessage);
                option = Handler.inputChecker(scanner, 11);
            }
            System.out.println("Your pizza has been confirmed. If you want to update anything else, " +
                    "please see the instructions below:");
        }
    }

    private static void updateDrink(Scanner scanner, Order myOrder) {
        System.out.println("Here is all the drinks that you have ordered:");
        StringBuilder allDrink = new StringBuilder();
        myOrder.allDrinkToString(allDrink);
        System.out.println(allDrink.toString());
        System.out.println("You can update the quantity of any drinks that you have ordered or add new drinks.\n" +
                "Please enter " + Handler.convertSetToString(PizzaParlour.allDrinks) + ".");
        System.out.println("[Note]: Please use format: <drink:quantity>.");
        System.out.println("If you want to delete this drink, please enter drink:0");
        System.out.println(exitMessage);
        List<String> drinkToQuantity = Handler.getQuantity(scanner, 2);
        myOrder.updateDrink(drinkToQuantity.get(0), Integer.parseInt(drinkToQuantity.get(1)));
        System.out.println("This drink has been updated.");
    }

    private static Integer pizzaNumberInputChecker(Scanner scanner, Order myOrder) {
        String option = scanner.nextLine();
        if (option.equals("Exit")) {
            System.exit(0);
        }
        Integer outputNum = null;
        while (outputNum == null) {
            try {
                Integer pizzaNum = Integer.parseInt(option);
                if (pizzaNum > myOrder.getPizzaList().size() || pizzaNum <= 0) {
                    System.out.println("Pizza number out of range. Please try again.");
                    option = scanner.nextLine();
                } else {
                    outputNum = pizzaNum;
                }
            } catch (Exception e) {
                if (option.equals("Add")) {
                    outputNum = 0;
                } else {
                    System.out.println("Invalid input. Please enter an integer or Add.");
                    option = scanner.nextLine();
                }
            }
        }
        return outputNum;
    }

}
