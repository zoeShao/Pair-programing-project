package a2.Controller;

import a2.Model.Menu;
import a2.Model.Order;
import a2.Model.Pizza;
import a2.PizzaParlour;

import java.util.*;

public class OrderMaker {
    private static String MenuInstruction = "If you want to see the menu, please enter Menu.";
    private static String ExitInstruction = "If you want to exit the program, please enter Exit.";
    private static Pizza.PizzaBuilder pizzaBuilder = new Pizza.PizzaBuilder();
    private static String sizeOption = Handler.convertSetToString(PizzaParlour.allSizes);
    private static String typeOption = Handler.convertSetToString(PizzaParlour.allTypes);
    private static String toppingOption = Handler.convertSetToString(PizzaParlour.allToppings);
    private static String drinkOption = Handler.convertSetToString(PizzaParlour.allDrinks);

    public static void makeOrder(Scanner scanner, Order myOrder) {
        System.out.println("What do you want to order, pizza or drink? Please enter Pizza or Drink.");
        System.out.println(ExitInstruction);
        String option = Handler.inputChecker(scanner, 1);
        if (option.equals("Pizza")) {
            orderPizza(scanner, myOrder);
        } else if (option.equals("Drink")) {
            orderDrink(scanner,myOrder);
        }

    System.out.println(
        "What else do you want to order? Please enter Pizza or Drink.\n"
            + "If you want to place the order, please enter Submit");
        System.out.println(MenuInstruction + "\n" + ExitInstruction);
        while (!(option = Handler.inputChecker(scanner, 0)).equals("Submit")) {
            if (option.equals("Menu")) {
                readMenu(scanner);
                System.out.println("Menu");
            } else if (option.equals("Pizza")) {
                orderPizza(scanner, myOrder);

            } else if (option.equals("Drink")) {
                orderDrink(scanner, myOrder);

            }
            System.out.println("What else do you want to order? Please enter Pizza or Drink.\n"
                    + "If you want to place the order, please enter Submit");
            System.out.println(MenuInstruction + "\n" + ExitInstruction);
        }
    }

    public static void orderPizza(Scanner scanner, Order myOrder) {

        System.out.println("What size do you want? Please enter " + sizeOption + ".");
        System.out.println(ExitInstruction);
        String option = Handler.inputChecker(scanner, 2);
        if (PizzaParlour.allSizes.contains(option)) {
            pizzaBuilder.setSize(option);
        }

        System.out.println("What type do you want? Please enter " + typeOption + ".");
        System.out.println(ExitInstruction);
        option = Handler.inputChecker(scanner, 3);
        if (PizzaParlour.allTypes.contains(option)) {
            pizzaBuilder.setType(option);
        }

        System.out.println("Do you want to add toppings? Please enter Yes/No.");
        System.out.println(ExitInstruction);
        option = Handler.inputChecker(scanner, 4);
        while (option.equals("Yes")) {
            System.out.println("Do you want to add more toppings? " +
                    "Please enter " + toppingOption + ":<quantity of the topping>");
            System.out.println("e.g. Pepperoni:3");
            System.out.println(ExitInstruction);
            List<String> toppingToQuantity = Handler.getQuantity(scanner, 1);
            pizzaBuilder.updateToppings(toppingToQuantity.get(0), Integer.parseInt(toppingToQuantity.get(1)));

            System.out.println("Do you want to add more toppings? Please enter Yes/No.");
            System.out.println(ExitInstruction);
            option = Handler.inputChecker(scanner, 4);
        }
        if (option.equals("No")) {
            Pizza newPizza = pizzaBuilder.build();
            myOrder.addPizza(newPizza);
            System.out.println("[Notice]: You have added a pizza to cart.");
        }
    }

    public static void orderDrink(Scanner scanner, Order myOrder) {
        String drinkOption = Handler.convertSetToString(PizzaParlour.allDrinks);
        System.out.println("Please enter with the format: <drink name(" + drinkOption + "):quantity of the drink(number)>");
        System.out.println("e.g. Coke:5");
        System.out.println(ExitInstruction);
        List<String> drinkToQuantity = Handler.getQuantity(scanner, 2);
        myOrder.updateDrink(drinkToQuantity.get(0), Integer.parseInt(drinkToQuantity.get(1)));
        System.out.println("[Notice]: You have added the drink(s) to cart.");
    }

    public static void readMenu(Scanner scanner) {
        System.out.println("Do you want to see the full menu or the price of a specific item? Please enter Full/Item.");
        System.out.println(ExitInstruction);
        String option = Handler.inputChecker(scanner, 6);
        if (option.equals("Item")) {
            readItemPrice(scanner);
            System.out.println("Do you still want to see the price of a specific item? Please enter Yes/No.");
            System.out.println(ExitInstruction);
            option = Handler.inputChecker(scanner, 4);

            while (option.equals("Yes")) {
                readItemPrice(scanner);
                System.out.println("Do you still want to see the price of a specific item? Please enter Yes/No.");
                System.out.println(ExitInstruction);
                option = Handler.inputChecker(scanner, 4);
            }
        }
        if (option.equals("Full")) {
            System.out.println(Menu.getFullMenu());
        }
    }

    private static void readItemPrice(Scanner scanner) {
        System.out.println("Which type of item's price do you want to see? Please enter Pizza/Topping/Drink.");
        System.out.println(ExitInstruction);
        String option = Handler.inputChecker(scanner, 7);

        if (option.equals("Pizza")) {
            System.out.println("Which type of pizza's price do you want to see? Please enter <size:(" +
                    sizeOption + ") type:(" + typeOption + ")>.");
            System.out.println("e.g. Medium Neapolitan\n" +
                    "(If you don't want to see pizza's price anymore, just enter End.)");
            System.out.println(ExitInstruction);
            option = scanner.nextLine();
            List<String> sizeToType;
            while (!option.equals("End")) {
                while ((sizeToType = Handler.pizzaPriceInputParser(option)) == null) {
                    option = scanner.nextLine();
                }
                String sizeAndType = sizeToType.get(0) + " " + sizeToType.get(1);
                System.out.println(sizeAndType +  "Pizza's price is: " + Menu.getPizzaPrice(sizeAndType));
                System.out.println("(If you don't want to see pizza's price anymore, just enter End.)\n" +
                        "Otherwise, please enter in the format: <size type> or just enter Exit to exit the program.");
                option = scanner.nextLine();
            }

        } else if (option.equals("Topping")) {
            System.out.println("Enter one of " + toppingOption +
                    " to see the price of the topping.\n" +
                    "(If you don't want to see topping's price anymore, just enter End.)");
            System.out.println(ExitInstruction);
            option = Handler.inputChecker(scanner, 8);
            while (!option.equals("End")) {
                if (PizzaParlour.allToppings.contains(option)) {
                    System.out.println(option + "'s Price is: " + Menu.getToppingPrice(option));
                }
                System.out.println("(If you don't want to see topping's price anymore, just enter End.)\n" +
                        "Otherwise, please enter: <topping name> or just enter Exit to exit the program.");
                option = Handler.inputChecker(scanner, 8);
            }

        } else if (option.equals("Drink")) {
            System.out.println("Enter one of " + drinkOption +
                    " to see the price of the drink.\n" +
                    "(If you don't want to see drink's price anymore, just enter End.)");
            System.out.println(ExitInstruction);
            option = Handler.inputChecker(scanner, 9);
            while (!option.equals("End")) {
                if (PizzaParlour.allDrinks.contains(option)) {
                    System.out.println(option + "'s Price is: " + Menu.getDrinkPrice(option));
                }
                System.out.println("(If you don't want to see drink's price anymore, just enter End.)\n" +
                        "Otherwise, please enter: <drink name> or just enter Exit to exit the program.");
                option = Handler.inputChecker(scanner, 9);
            }
        }
    }
}
