package a2.Controller;

import a2.Model.Order;
import a2.Model.Pizza;

import java.util.*;

public class OrderMaker {
    private static String MenuInstruction = "If you want to see the menu, please enter Menu.";
    private static String ExitInstruction = "If you want to exit the program, please enter Exit.";

    public static void makeOrder(Scanner scanner, Order myOrder) {
        System.out.println("What do you want to order, pizza or drink? Please enter Pizza or Drink.");
        System.out.println(ExitInstruction);
        String option = inputChecker(scanner, 1);
        if (option.equals("Pizza")) {
            orderPizza(scanner, myOrder);
            System.out.println("Pizza");
        } else if (option.equals("Drink")) {
            System.out.println("Drink");
        } else if (option.equals("Exit")){
            System.exit(0);
        }

        System.out.println("What else do you want to order? Please enter Pizza or Drink.\n"
                + "If you want to place the order, please enter Submit");
        System.out.println(MenuInstruction + ExitInstruction);
        while (!(option = inputChecker(scanner, 0)).equals("Submit")) {
            if (option.equals("Menu")) {
                System.out.println("Menu");
            } else if (option.equals("Pizza")) {
                orderPizza(scanner, myOrder);
                System.out.println("Pizza");
            } else if (option.equals("Drink")) {
                System.out.println("Drink");
            } else if (option.equals("Exit")) {
                System.exit(0);
            }
            System.out.println("What else do you want to order? Please enter Pizza or Drink.\n"
                    + "If you want to place the order, please enter Submit");
            System.out.println(MenuInstruction + ExitInstruction);
        }
        if (option.equals("Submit")) {
            myOrder.calculateTotalPrice();
            System.out.println("Order Submitted.");
        }

    }

    private static String inputChecker(Scanner scanner, Integer flag) {
        Set<String> validInput = new HashSet<String>();

        validInput.add("Exit");
        String errorMessage;
        switch (flag) {
            case 0:
                validInput.add("Pizza");
                validInput.add("Drink");
                validInput.add("Menu");
                validInput.add("Submit");
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 1:
                validInput.add("Pizza");
                validInput.add("Drink");
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 2:
                validInput.addAll(Pizza.allSizes);
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 3:
                validInput.addAll(Pizza.pizzaTypeToRecipe.keySet());
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 4:
                validInput.add("Yes");
                validInput.add("No");
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            default:
                errorMessage = "Invalid flag.";
        }
        String option = scanner.nextLine();
        while (!(validInput.contains(option))) {
            System.out.println(errorMessage);
            option = scanner.nextLine();
        }
        return option;
    }

    private static void orderPizza(Scanner scanner, Order myOrder) {
        Pizza.PizzaBuilder pizzaBuilder = new Pizza.PizzaBuilder();
        String sizeOption = convertSetToString(Pizza.allSizes);
        String typeOption = convertSetToString(Pizza.pizzaTypeToRecipe.keySet());
        String toppingOption = convertSetToString(Pizza.allToppings);

        System.out.println("What size do you want? Please enter " + sizeOption + ".");
        System.out.println(ExitInstruction);
        String option = inputChecker(scanner, 2);
        if (Pizza.allSizes.contains(option)) {
            pizzaBuilder.setSize(option);
            System.out.println(option);

        } else if (option.equals("Exit")) {
            System.exit(0);
        }

        System.out.println("What type do you want? Please enter " + typeOption + ".");
        System.out.println(ExitInstruction);
        option = inputChecker(scanner, 3);
        if (Pizza.pizzaTypeToRecipe.keySet().contains(option)) {
            pizzaBuilder.setType(option);
            System.out.println(option);

        } else if (option.equals("Exit")) {
            System.exit(0);
        }

        System.out.println("Do you want to add toppings? Please enter Yes/No.");
        System.out.println(ExitInstruction);
        option = inputChecker(scanner, 4);
        while (option.equals("Yes")) {
            System.out.println("Do you want to add more toppings? " +
                    "Please enter " + toppingOption + ":<quantity of the topping>");
            System.out.println("e.g. Pepperoni:3");
            option = scanner.nextLine();
            List<String> toppingToQuantity;
            while ((toppingToQuantity = toppingInputParser(option)) == null) {
                option = scanner.nextLine();
            }
            pizzaBuilder.updateToppings(toppingToQuantity.get(0), Integer.parseInt(toppingToQuantity.get(1)));
            System.out.println(toppingToQuantity);
            System.out.println("Do you want to add toppings? Please enter Yes/No.");
            System.out.println(ExitInstruction);
            option = inputChecker(scanner, 4);
        }
        if (option.equals("No")) {
            Pizza newPizza = pizzaBuilder.build();
            myOrder.addPizza(newPizza);
            System.out.println("This pizza order is completed.");
        } else if (option.equals("Exit")) {
            System.exit(0);
        }
    }

    private static String convertSetToString(Set<String> inputSet) {
        StringBuilder outputMessage = new StringBuilder();
        for (String element: inputSet) {
            outputMessage.append(element).append("/");
        }
        return outputMessage.substring(0, outputMessage.length()-1);
    }

    private static List<String> toppingInputParser(String input) {
        String toppingOption = convertSetToString(Pizza.allToppings);
        if (!input.contains(":")) {
            System.out.println("Invalid input. Please separate topping and quantity by :");
            return null;
        }
        String[] parts = input.split(":");
        if (parts.length != 2) {
            System.out.println("Invalid input." + "Please enter " + toppingOption + ":<quantity of the topping>");
            return null;
        }
        String topping = parts[0];
        if (!Pizza.allToppings.contains(topping)) {
            System.out.println("Invalid topping." + "Please enter " + toppingOption + ":<quantity of the topping>");
            return null;
        }
        try{
            Integer quantity = Integer.parseInt(parts[1]);
            List<String> paresedOutput = new ArrayList<String>();
            paresedOutput.add(parts[0]);
            paresedOutput.add(parts[1]);
            return paresedOutput;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a integer as quantity.");
            return null;
        }
    }
}
