package a2.Model.PizzaFactory;

import a2.Model.Pizza.Pizza;

public class PizzaFactoryProducer {
    private PizzaFactory pizzaFactory;


    public PizzaFactoryProducer(PizzaFactory pizzaFactory){
        this.pizzaFactory = pizzaFactory;
    }

    public PizzaFactory getPizzaFactory(){
        System.out.println("get Pizza factory");
        return this.pizzaFactory;
    }

}
