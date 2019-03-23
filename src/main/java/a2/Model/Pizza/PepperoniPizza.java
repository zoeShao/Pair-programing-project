package a2.Model.Pizza;

public class PepperoniPizza extends AbstractPizza {

    // Builder for Pizza
    public static class PepperoniPizzaBuilder extends AbstractPizzaBuilder{
        public PepperoniPizzaBuilder prepare(){
            // test builder
            System.out.print("Prepared pepperoni pizza successful.");
            return this;
        }
        public PepperoniPizza build(){
            // test builder
            System.out.print("PepperoniPizza");
            return new PepperoniPizza();
        }
    }

}
