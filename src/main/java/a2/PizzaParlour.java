package a2;
import java.util.Scanner;

public class PizzaParlour {

    public static void main(String[] args) {

        System.out.println("Welcome to 301 Pizza!: ");
        System.out.println("Would you like to order in, or make a delivery order?: ");
        Scanner scanner = new Scanner(System.in);
        String orderType = scanner.nextLine();

        scanner.close();

    }

}