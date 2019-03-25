package a2.Controller;

import a2.PizzaParlour;

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
                validInput.addAll(PizzaParlour.allSizes);
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 3:
                validInput.addAll(PizzaParlour.allTypes);
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
            case 6:
                validInput.add("Full");
                validInput.add("Item");
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 7:
                validInput.add("Pizza");
                validInput.add("Topping");
                validInput.add("Drink");
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 8:
                validInput.addAll(PizzaParlour.allToppings);
                validInput.add("End");
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 9:
                validInput.addAll(PizzaParlour.allDrinks);
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                validInput.add("End");
                break;
            case 10:
                validInput.add("Update");
                validInput.add("Confirm");
                validInput.add("Cancel");
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 11:
                validInput.add("Size");
                validInput.add("Type");
                validInput.add("Topping");
                validInput.add("Delete");
                validInput.add("Confirm");
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 12:
                validInput.add("Pickup");
                validInput.add("Delivery");
                errorMessage = "Invalid Input. Please enter " + convertSetToString(validInput) + ".";
                break;
            case 13:
                validInput.add("InHouse");
                validInput.add("UberEats");
                validInput.add("Foodora");
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
        if (option.equals("Exit")) {
            System.exit(0);
        }
        return option;
    }

    static String convertSetToString(Set<String> inputSet) {
        StringBuilder outputMessage = new StringBuilder();
        for (String element: inputSet) {
            outputMessage.append(element).append("/");
        }
        return outputMessage.substring(0, outputMessage.length()-1);
    }

    private static List<String> inputParser(String input, Set<String> options) {
        String optionMessage = Handler.convertSetToString(options);
        if (input.equals("Exit")){
            System.exit(0);
        }
        if (!input.contains(":")) {
            System.out.println("Invalid input. Please separate topping and quantity by :");
            System.out.println("If you want to exit the program, please enter Exit");
            return null;
        }
        String[] parts = input.split(":");
        if (parts.length != 2) {
            System.out.println("Invalid input. " + "Please enter " + optionMessage + ":<quantity>");
            System.out.println("If you want to exit the program, please enter Exit");
            return null;
        }
        String topping = parts[0];
        if (!options.contains(topping)) {
            System.out.println("Invalid input. " + "Please enter " + optionMessage + ":<quantity>");
            System.out.println("If you want to exit the program, please enter Exit");
            return null;
        }
        try{
            Integer quantity = Integer.parseInt(parts[1]);
            if (quantity < 0) {
                System.out.println("Invalid input. Please enter a positive integer as quantity.");
                System.out.println("If you want to exit the program, please enter Exit");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a integer as quantity.");
            System.out.println("If you want to exit the program, please enter Exit");
            return null;
        }
        List<String> paresedOutput = new ArrayList<String>();
        paresedOutput.add(parts[0]);
        paresedOutput.add(parts[1]);
        return paresedOutput;
    }

    static List<String> pizzaPriceInputParser(String sizeAndType) {
        if (sizeAndType.equals("Exit")){
            System.exit(0);
        }
        String[] parts = sizeAndType.split(" ");
        if (parts.length != 2) {
            System.out.println("Invalid input. Please enter like this: Small Pepperoni");
            System.out.println("If you want to exit the program, please enter Exit");
            return null;
        }
        String type = parts[1];
        if (!PizzaParlour.allTypes.contains(type)){
            System.out.println("Invalid type: " + type + ". Please try again.");
            System.out.println("If you want to exit the program, please enter Exit");
            return null;
        }
        String size = parts[0];
        if (!PizzaParlour.allSizes.contains(size)) {
            System.out.println("size must be Small/Medium/Large. Please try again.");
            System.out.println("If you want to exit the program, please enter Exit");
            return null;
        }
        List<String> parsedOutput = new ArrayList<String>();
        parsedOutput.add(parts[0]);
        parsedOutput.add(parts[1]);
        return parsedOutput;
    }

    static List<String> getQuantity(Scanner scanner, int flag){
        Set<String> inputSet;
        switch (flag) {
            case 1:
                inputSet =  PizzaParlour.allToppings;
                break;
            case 2:
                inputSet = PizzaParlour.allDrinks;
                break;
            default:
                inputSet = new HashSet<String>();
                break;
        }
        String option = scanner.nextLine();
        List<String> quantity;
        while ((quantity = Handler.inputParser(option, inputSet)) == null) {
            option = scanner.nextLine();
        }
        return quantity;
    }
}
