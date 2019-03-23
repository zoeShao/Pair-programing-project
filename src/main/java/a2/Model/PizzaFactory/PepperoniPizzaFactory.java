package a2.Model.PizzaFactory;

import a2.Model.Pizza.PepperoniPizza;
import a2.Model.Pizza.Pizza;

public class PepperoniPizzaFactory implements PizzaFactory{
    public Pizza getPizza() {
        return new PepperoniPizza.PepperoniPizzaBuilder().prepare().build();
    }
}
