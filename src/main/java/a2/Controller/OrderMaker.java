package a2.Controller;

import a2.Model.AllData;
import a2.Model.Order;
import a2.Model.Pizza;

import java.util.*;

public class OrderMaker {
    private static String MenuInstruction = "If you want to see the menu, please enter Menu.";
    private static String ExitInstruction = "If you want to exit the program, please enter Exit.";

    public static void makeOrder(Scanner scanner, Order myOrder) {
        System.out.println("What do you want to order, pizza or drink? Please enter Pizza or Drink.");
        System.out.println(ExitInstruction);
        String option = Handler.inputChecker(scanner, 1);
        if (option.equals("Pizza")) {
            orderPizza(scanner, myOrder);
        } else if (option.equals("Drink")) {
            orderDrink(scanner,myOrder);
        } else if (option.equals("Exit")){
            System.exit(0);
        }

        System.out.println("What else do you want to order? Please enter Pizza or Drink.\n"
                + "If you want to place the order, please enter Submit");
        System.out.println(MenuInstruction + ExitInstruction);
        while (!(option = Handler.inputChecker(scanner, 0)).equals("Submit")) {
            if (option.equals("Menu")) {
                System.out.println("Menu");
            } else if (option.equals("Pizza")) {
                orderPizza(scanner, myOrder);

            } else if (option.equals("Drink")) {
                orderDrink(scanner,myOrder);

            } else if (option.equals("Exit")) {
                System.exit(0);
            }
            System.out.println("What else do you want to order? Please enter Pizza or Drink.\n"
                    + "If you want to place the order, please enter Submit");
            System.out.println(MenuInstruction + ExitInstruction);
        }
        myOrder.calculateTotalPrice();
        System.out.println("Order Submitted.");

    }

    private static void orderPizza(Scanner scanner, Order myOrder) {
        Pizza.PizzaBuilder pizzaBuilder = new Pizza.PizzaBuilder();
        String sizeOption = Handler.convertSetToString(AllData.allSizes);
        String typeOption = Handler.convertSetToString(Pizza.pizzaTypeToRecipe.keySet());
        String toppingOption = Handler.convertSetToString(AllData.allToppings);

        System.out.println("What size do you want? Please enter " + sizeOption + ".");
        System.out.println(ExitInstruction);
        String option = Handler.inputChecker(scanner, 2);
        if (AllData.allSizes.contains(option)) {
            pizzaBuilder.setSize(option);
        } else if (option.equals("Exit")) {
            System.exit(0);
        }

        System.out.println("What type do you want? Please enter " + typeOption + ".");
        System.out.println(ExitInstruction);
        option = Handler.inputChecker(scanner, 3);
        if (Pizza.pizzaTypeToRecipe.keySet().contains(option)) {
            pizzaBuilder.setType(option);
        } else if (option.equals("Exit")) {
            System.exit(0);
        }

        System.out.println("Do you want to add toppings? Please enter Yes/No.");
        System.out.println(ExitInstruction);
        option = Handler.inputChecker(scanner, 4);
        while (option.equals("Yes")) {
            System.out.println("Do you want to add more toppings? " +
                    "Please enter " + toppingOption + ":<quantity of the topping>");
            System.out.println("e.g. Pepperoni:3");
            option = scanner.nextLine();
            List<String> toppingToQuantity;
            while ((toppingToQuantity = Handler.inputParser(option, AllData.allToppings)) == null) {
                option = scanner.nextLine();
            }
            pizzaBuilder.updateToppings(toppingToQuantity.get(0), Integer.parseInt(toppingToQuantity.get(1)));

            System.out.println("Do you want to add toppings? Please enter Yes/No.");
            System.out.println(ExitInstruction);
            option = Handler.inputChecker(scanner, 4);
        }
        if (option.equals("No")) {
            Pizza newPizza = pizzaBuilder.build();
            myOrder.addPizza(newPizza);
            System.out.println("This pizza order is completed.");
        } else if (option.equals("Exit")) {
            System.exit(0);
        }
    }

    private static void orderDrink(Scanner scanner, Order myOrder) {
        String drinkOption = Handler.convertSetToString(AllData.allDrinks);
        System.out.println("Do you want to add any drinks? Please enter Yes/No.");
        System.out.println(ExitInstruction);
        String option = Handler.inputChecker(scanner, 4);
        while (option.equals("Yes")) {
            System.out.println("Please enter " + drinkOption + ":<quantity of the drink>");
            System.out.println("e.g. Coke:5");
            option = scanner.nextLine();
            List<String> drinkToQuantity;
            while ((drinkToQuantity = Handler.inputParser(option, AllData.allDrinks)) == null) {
                option = scanner.nextLine();
            }
            myOrder.updateDrink(drinkToQuantity.get(0), Integer.parseInt(drinkToQuantity.get(1)));

            System.out.println("Do you want to add any drinks? Please enter Yes/No.");
            System.out.println(ExitInstruction);
            option = Handler.inputChecker(scanner, 4);
        }
        if (option.equals("No")) {
            System.out.println("Drinks order is completed.");
        } else if (option.equals("Exit")) {
            System.exit(0);
        }
    }

}
