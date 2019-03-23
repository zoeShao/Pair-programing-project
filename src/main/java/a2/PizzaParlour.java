package a2;
import a2.Model.Pizza.PepperoniPizza;
import a2.Model.Pizza.Pizza;
import a2.Model.PizzaFactory.PizzaFactory;
import a2.Model.PizzaFactory.PizzaFactoryProducer;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class PizzaParlour {

    private static PizzaFactory createPizzaFactory(String className) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException{
        Class<?> clazz = Class.forName(className);
        return (PizzaFactory) clazz.getConstructor().newInstance();
    }

    public static void main(String[] args) {

        System.out.println("Welcome to 301 Pizza!: ");
        System.out.println("Would you like to order in, or make a delivery order?: ");
        Scanner scanner = new Scanner(System.in);
        String orderType = scanner.nextLine();
        try{
            new PizzaFactoryProducer(createPizzaFactory("a2.Model.PizzaFactory.PepperoniPizzaFactory")).getPizzaFactory().getPizza();
        } catch (Exception e){
            System.out.println("Ooops ... " + e.getMessage());
        }
        // test builder
        Pizza p = new PepperoniPizza.PepperoniPizzaBuilder().prepare().build();
        scanner.close();

    }

}