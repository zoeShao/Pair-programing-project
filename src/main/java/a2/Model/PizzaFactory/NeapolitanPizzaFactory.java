package a2.Model.PizzaFactory;

import a2.Model.Pizza.NeapolitanPizza;
import a2.Model.Pizza.Pizza;

public class NeapolitanPizzaFactory implements PizzaFactory{
    public Pizza getPizza() {
        return new NeapolitanPizza.NeapolitanPizzaBuilder().prepare().build();
    }
}
