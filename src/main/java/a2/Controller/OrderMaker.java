package a2.Controller;

import a2.Model.Order;
import a2.Model.Pizza;

import java.util.*;

public class OrderMaker {
    private static String instruction = "If you want to see the menu, please enter Menu.\n"
            + "If you want to exit the program, please enter Exit.";
    public static void makeOrder(Scanner scanner) {
        System.out.println("What do you want to order, pizza or drink? Please enter Pizza or Drink.");
        System.out.println("If you want to exit the program, please enter Exit.");
        String option = inputChecker(scanner, 1);
        if (option.equals("Pizza")) {
            orderPizza(scanner);
            System.out.println("Pizza");
        } else if (option.equals("Drink")) {
            System.out.println("Drink");
        } else if (option.equals("Exit")){
            System.exit(0);
        }

        System.out.println("What else do you want to order? Please enter Pizza or Drink.\n"
                + "If you want to place the order, please enter Submit");
        System.out.println(instruction);
        while (!(option = scanner.nextLine()).equals("Exit")) {
            if (option.equals("Menu")) {
                System.out.println("Menu");
            } else if (option.equals("Pizza")) {
                orderPizza(scanner);
                System.out.println("Pizza");
                System.out.println("What else do you want to order? Please enter Pizza or Drink.\n"
                        + "If you want to place the order, please enter Submit");
                System.out.println(instruction);
            } else if (option.equals("Drink")) {
                System.out.println("Drink");
                System.out.println("What else do you want to order? Please enter Pizza or Drink.\n"
                        + "If you want to place the order, please enter Submit");
                System.out.println(instruction);
            } else {
                System.out.println("Invalid Input. Please enter Menu/Pizza/Drink/Exit.");
            }

        }
        System.exit(0);
    }

    private static String inputChecker(Scanner scanner, Integer flag) {
        Set<String> validInput = new HashSet<String>();
        String errorMessage;
        switch (flag) {
            case 0:
                validInput.add("Menu");
                validInput.add("Pizza");
                validInput.add("Drink");
                validInput.add("Exit");
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 1:
                validInput.add("Pizza");
                validInput.add("Drink");
                validInput.add("Exit");
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 2:
                validInput = Pizza.allSizes;
                validInput.add("Menu");
                validInput.add("Exit");
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 3:
                validInput.add("Menu");
                validInput.add("Exit");
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

    private static void orderPizza(Scanner scanner) {
        String sizeOption = convertSetToString(Pizza.allSizes);
        String typeOption = convertSetToString(Pizza.pizzaTypeToRecipe.keySet());
        String toppingOption = convertSetToString(Pizza.allToppings);
//        System.out.println(optionMessage);

        System.out.println("What size do you want? Please enter " + sizeOption + ".");
        System.out.println(instruction);
        String option = inputChecker(scanner, 2);
        if (Pizza.allSizes.contains(option)) {
            System.out.println(option);
        } else if (option.equals("Menu")) {
            System.out.println(option);
        } else if (option.equals("Exit")) {
            System.exit(0);
        }

        System.out.println("What type do you want? Please enter " + typeOption + ".");
        option = inputChecker(scanner, 3);
        if (Pizza.pizzaTypeToRecipe.keySet().contains(option)) {
            System.out.println(option);
        } else if (option.equals("Menu")) {
            System.out.println(option);
        } else if (option.equals("Exit")) {
            System.exit(0);
        }

        System.out.println("Do you want to add toppings? Please enter Yes/No.");
        option = inputChecker(scanner, 4);
        if (option.equals("Yes")) {
            System.out.println("Do you want to add more toppings? " +
                    "Please enter " + toppingOption + ":<quantity of the topping>");
            System.out.println("e.g. Pepperoni:3");
            option = scanner.nextLine();
            Map<String, Integer> toppingToQuantity;
            while ((toppingToQuantity = toppingInputParser(option)) == null) {
                option = scanner.nextLine();
            }
            System.out.println(toppingToQuantity);
        } else if (option.equals("No")) {
            System.out.println("This pizza order is completed.");
        }
    }

    private static String convertSetToString(Set<String> inputSet) {
        StringBuilder outputMessage = new StringBuilder();
        for (String element: inputSet) {
            outputMessage.append(element).append("/");
        }
        return outputMessage.substring(0, outputMessage.length()-1);
    }

    private static Map<String, Integer> toppingInputParser(String input) {
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
            Map parsedInput = new HashMap<String, Integer>();
            parsedInput.put(topping, quantity);
            return parsedInput;
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a integer as quantity.");
            return null;
        }
    }
}
