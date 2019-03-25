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
        calculateTotalPrice();
    }

    public void updateDrink(String name, Integer quantity) {
        if (quantity > 0) {
            this.drinkToQuantity.put(name, quantity);
        } else {
            this.drinkToQuantity.remove(name);
        }
        calculateTotalPrice();
    }

    public Integer getOrderNum(){
        return this.orderNum;
    }

    public double getTotalPrice(){
        return this.totalPrice;
    }

    public List<Pizza> getPizzaList(){
        return this.pizzaList;
    }

    private void calculateTotalPrice() {
        double totalPrice = 0.0;
        for (Pizza pizza: this.pizzaList) {
            totalPrice += pizzaTypeToSizeToPrice.get(pizza.getType()).get(pizza.getSize());
            for (String topping: pizza.getToppings().keySet()) {
                totalPrice += toppingToPrice.get(topping) * pizza.getToppings().get(topping);
            }
        }
        for (String drink: this.drinkToQuantity.keySet()) {
            totalPrice += drinkToPrice.get(drink) * this.drinkToQuantity.get(drink);
        }
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder orderContent = new StringBuilder("Order Number: #" + this.orderNum.toString() + "\n");
        orderContent.append("Pizzas:\n");
        allPizzaToString(orderContent);
        orderContent.append("Drinks:\n");
        orderContent.append("    ");
        allDrinkToString(orderContent);
        orderContent.append("\n");
        orderContent.append("Total price: ").append("$").append(this.totalPrice);
        return orderContent.toString();
    }

    public void allPizzaToString(StringBuilder orderContent) {
        for (int i = 0; i < pizzaList.size(); i++) {
            String pizzaNumber = ((Integer)(i+1)).toString();
            orderContent.append("    ").append(pizzaNumber).append(". ").append(pizzaList.get(i).toString()).append("\n");
        }
    }

    public void allDrinkToString(StringBuilder orderContent) {
        for (String drink: this.drinkToQuantity.keySet()) {
            orderContent.append(drink).append("*").append(this.drinkToQuantity.get(drink)).append("  ");
        }
    }

    public void updatePizzaByIndex(int i, int flag, String inputString, int inputNumber) {
        Pizza pizza = this.pizzaList.get(i);
        Pizza newPizza;
        switch (flag) {
            case 0:
                this.pizzaList.remove(i);
                break;
            case 1:
                newPizza = new Pizza.PizzaBuilder()
                        .setSize(inputString)
                        .setType(pizza.getType())
                        .setToppings(pizza.getToppings()).build();
                this.pizzaList.remove(i);
                this.pizzaList.add(i, newPizza);
                break;
            case 2:
                newPizza = new Pizza.PizzaBuilder()
                        .setSize(pizza.getSize())
                        .setType(inputString)
                        .setToppings(pizza.getToppings()).build();
                this.pizzaList.remove(i);
                this.pizzaList.add(i, newPizza);
                break;
            case 3:
                newPizza = new Pizza.PizzaBuilder()
                        .setSize(pizza.getSize())
                        .setType(inputString)
                        .setToppings(pizza.getToppings())
                        .updateToppings(inputString, inputNumber)
                        .build();
                this.pizzaList.remove(i);
                this.pizzaList.add(i, newPizza);
                break;
            default:
                break;
        }
        calculateTotalPrice();
    }
}
