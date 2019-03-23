package a2.Model.Pizza;

public class MargheritaPizza extends AbstractPizza {
    // Builder for Pizza
    public static class MargheritaPizzaBuilder extends AbstractPizzaBuilder{
        public MargheritaPizzaBuilder prepare(){
            // test builder
            System.out.print("Prepared margherita pizza successful.");
            return this;
        }
        public MargheritaPizza build(){
            // test builder
            System.out.print("MargheritaPizza");
            return new MargheritaPizza();
        }
    }
}
