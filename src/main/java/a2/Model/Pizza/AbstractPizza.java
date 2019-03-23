package a2.Model.Pizza;

import java.util.List;

public abstract class AbstractPizza implements Pizza{
    private String size;
    private List<String> toppings;

    public String getSize(){
        return this.size;
    }
    public List<String> getToppings(){
        return this.toppings;
    }
    public void setSize(String size){
        this.size = size;
    }
    public void setToppings(List<String> newToppings){
        this.toppings = newToppings;
    }

    public AbstractPizza(){}

    // Abstract Builder for Pizza
    public static abstract class AbstractPizzaBuilder {
        public abstract AbstractPizzaBuilder prepare();
        public abstract AbstractPizza build();
    }
}
