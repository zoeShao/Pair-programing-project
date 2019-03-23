package a2.Model.PizzaFactory;

import a2.Model.Pizza.Pizza;
import a2.Model.Pizza.VegetarianPizza;

public class VegetarianPizzaFactory implements PizzaFactory{

    public Pizza getPizza() {
        return new VegetarianPizza.VegetarianPizzaBuilder().prepare().build();
    }
}
