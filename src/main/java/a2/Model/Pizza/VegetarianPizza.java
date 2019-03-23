package a2.Model.Pizza;

public class VegetarianPizza extends AbstractPizza {
    // Builder for Pizza
    public static class VegetarianPizzaBuilder extends AbstractPizzaBuilder{
        public VegetarianPizzaBuilder prepare(){
            // test builder
            System.out.print("Prepared vegetarian pizza successful.");
            return this;
        }
        public VegetarianPizza build(){
            // test builder
            System.out.print("VegetarianPizza");
            return new VegetarianPizza();
        }
    }
}
