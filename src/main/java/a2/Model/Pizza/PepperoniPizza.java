package a2.Model.Pizza;

public class PepperoniPizza extends AbstractPizza {

    // Builder for Pizza
    public static class PepperoniPizzaBuilder extends AbstractPizzaBuilder{
        public PepperoniPizzaBuilder prepare(){
            // test builder
            System.out.println("Prepared pepperoni pizza successful.");
            return this;
        }
        public PepperoniPizza build(){
            // test builder
            System.out.println("PepperoniPizza");
            return new PepperoniPizza();
        }
    }

}
