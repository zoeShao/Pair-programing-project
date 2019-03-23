package a2.Model.Pizza;

public class NeapolitanPizza extends AbstractPizza {
    // Builder for Pizza
    public static class NeapolitanPizzaBuilder extends AbstractPizzaBuilder{
        public NeapolitanPizzaBuilder prepare(){
            // test builder
            System.out.print("Prepared neapolitan pizza successful.");
            return this;
        }
        public NeapolitanPizza build(){
            // test builder
            System.out.print("NeapolitanPizza");
            return new NeapolitanPizza();
        }
    }
}
