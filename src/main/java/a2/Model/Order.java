package a2.Model;

import java.util.*;

public class Order {
    private Integer orderNum;
    private List<Pizza> pizzaList;
    private Map<String, Integer> drinkToQuantity;
    public static Map<String, Map<String, Double>> pizzaTypeToSizeToPrice;
    public static Map<String, Double> toppingToPrice;
    public static Map<String, Double> drinkToPrice;
    private double totalPrice;


    public Order() {
        this.orderNum = 10000 + (new Random(System.currentTimeMillis())).nextInt(20000);
        this.pizzaList =  new ArrayList<Pizza>();
        this.drinkToQuantity = new HashMap<String, Integer>();
        this.totalPrice = 0.0;
    }

    public void addPizza(Pizza pizza) {
        this.pizzaList.add(pizza);
    }

    public void updateDrink(String name, Integer quantity) {
        if (quantity > 0) {
            this.drinkToQuantity.put(name, quantity);
        } else {
            this.drinkToQuantity.remove(name);
        }

    }

    public double calculateTotalPrice() {
        for (Pizza pizza: this.pizzaList) {
            this.totalPrice += pizzaTypeToSizeToPrice.get(pizza.getType()).get(pizza.getSize());
            for (String topping: pizza.getToppings().keySet()) {
                this.totalPrice += toppingToPrice.get(topping) * pizza.getToppings().get(topping);
            }
        }
        for (String drink: this.drinkToQuantity.keySet()) {
            this.totalPrice += drinkToPrice.get(drink) * this.drinkToQuantity.get(drink);
        }
        return this.totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder orderContent = new StringBuilder("Order Number: #" + this.orderNum.toString() + "\n");
        orderContent.append("Pizzas:\n");
        for (int i = 0; i < pizzaList.size(); i++) {
            String pizzaNumber = ((Integer)(i+1)).toString();
            orderContent.append("    ").append(pizzaNumber).append(". ").append(pizzaList.get(i).toString()).append("\n");
        }
        orderContent.append("Drinks:\n");
        orderContent.append("    ");
        for (String drink: this.drinkToQuantity.keySet()) {
            orderContent.append(drink).append("*").append(this.drinkToQuantity.get(drink)).append("  ");
        }
        orderContent.append("\n");
        orderContent.append("Total price: ").append("$").append(this.totalPrice);
        return orderContent.toString();
    }
}
