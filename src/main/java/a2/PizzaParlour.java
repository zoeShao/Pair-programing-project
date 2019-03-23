package a2;
import a2.Model.Pizza.PepperoniPizza;
import a2.Model.Pizza.Pizza;

import java.util.Scanner;

public class PizzaParlour {

    public static void main(String[] args) {

        System.out.println("Welcome to 301 Pizza!: ");
        System.out.println("Would you like to order in, or make a delivery order?: ");
        Scanner scanner = new Scanner(System.in);
        String orderType = scanner.nextLine();
        // test builder
        Pizza p = new PepperoniPizza.PepperoniPizzaBuilder().prepare().build();
        scanner.close();

    }

}