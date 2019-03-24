package a2.Controller;

import a2.Model.AllData;
import a2.Model.Pizza;

import java.util.*;

public class Handler {
    public static String inputChecker(Scanner scanner, Integer flag) {
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
                validInput.addAll(AllData.allSizes);
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
            case 5:
                validInput.add("Menu");
                validInput.add("Order");
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

    public static String convertSetToString(Set<String> inputSet) {
        StringBuilder outputMessage = new StringBuilder();
        for (String element: inputSet) {
            outputMessage.append(element).append("/");
        }
        return outputMessage.substring(0, outputMessage.length()-1);
    }

    public static List<String> inputParser(String input, Set<String> options) {
        String optionMessage = Handler.convertSetToString(options);
        if (!input.contains(":")) {
            System.out.println("Invalid input. Please separate topping and quantity by :");
            return null;
        }
        String[] parts = input.split(":");
        if (parts.length != 2) {
            System.out.println("Invalid input." + "Please enter " + optionMessage + ":<quantity>");
            return null;
        }
        String topping = parts[0];
        if (!options.contains(topping)) {
            System.out.println("Invalid topping." + "Please enter " + optionMessage + ":<quantity>");
            return null;
        }
        try{
            Integer quantity = Integer.parseInt(parts[1]);
            if (quantity < 0) {
                System.out.println("Invalid input. Please enter a positive integer as quantity.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a integer as quantity.");
            return null;
        }
        List<String> paresedOutput = new ArrayList<String>();
        paresedOutput.add(parts[0]);
        paresedOutput.add(parts[1]);
        return paresedOutput;
    }
}
