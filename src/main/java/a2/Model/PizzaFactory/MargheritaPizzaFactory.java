package a2.Model.PizzaFactory;

import a2.Model.Pizza.MargheritaPizza;
import a2.Model.Pizza.Pizza;

public class MargheritaPizzaFactory implements PizzaFactory{
    public Pizza getPizza() {
        return new MargheritaPizza.MargheritaPizzaBuilder().prepare().build();
    }
}
