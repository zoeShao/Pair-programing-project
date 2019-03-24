package a2.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pizza {
    private String size;
    private String type;
    private String recipe;  // specific method of preparation
    private Map<String, Integer> toppingToQuantity;
    public static Map<String, String> pizzaTypeToRecipe;
    public static Set<String> allToppings;

    // Builder for Pizza
    public static class PizzaBuilder {
        private String size;
        private String type;
        private Map<String, Integer> toppingToQuantity = new HashMap<String, Integer>();

        public PizzaBuilder setSize(String size){
            if (!size.equals("Small") & !size.equals("Medium") & !size.equals("Large")) {
                throw new IllegalArgumentException("size must be Small/Medium/Large. Please try again.");
            }
            this.size = size;
            return this;
        }

        public PizzaBuilder setType(String type){
            if (!Pizza.pizzaTypeToRecipe.keySet().contains(type)){
                throw new IllegalArgumentException("Invalid type: " + type + ". Please try again.");
            }
            this.type = type;
            return this;
        }

        public PizzaBuilder setToppings(Map<String, Integer> toppingToQuantity) {
            this.toppingToQuantity = toppingToQuantity;
            return this;
        }

        public PizzaBuilder updateToppings(String topping, Integer quantity){
            if (!Pizza.allToppings.contains(topping)){
                throw new IllegalArgumentException("Invalid topping: " + topping + ". Please try again.");
            }
            if (quantity < 0) {
                throw new IllegalArgumentException("Quantity must be greater than 0. Please try again.");
            }
            if (quantity > 0) {
                this.toppingToQuantity.put(topping, quantity);
            } else {
                this.toppingToQuantity.remove(topping);
            }
            return this;
        }

        public Pizza build(){
            return new Pizza(this);
        }
    }

    private Pizza(PizzaBuilder pizzaBuilder){
        this.size = pizzaBuilder.size;
        this.type = pizzaBuilder.type;
        this.recipe = Pizza.pizzaTypeToRecipe.get(this.type);
        this.toppingToQuantity = pizzaBuilder.toppingToQuantity;
    }

    public String getSize(){
        return this.size;
    }

    public String getType() {
        return this.type;
    }

    public String getRecipe() {
        return this.recipe;
    }

    public Map<String, Integer> getToppings(){
        return this.toppingToQuantity;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(this.size + " " + this.type + "Pizza with: ");
        for (String topping: this.toppingToQuantity.keySet()) {
            string.append(topping)
                    .append("*")
                    .append(this.toppingToQuantity.get(topping)).append("  ");
        }
        return string.toString();
    }
}
