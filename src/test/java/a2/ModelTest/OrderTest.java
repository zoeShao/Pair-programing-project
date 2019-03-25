package a2.ModelTest;

import a2.Model.Order;
import a2.Model.Pizza;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OrderTest {
    private Order order = new Order();
    private Pizza pizza;
    private Pizza.PizzaBuilder pizzaBuilder;
    private Pizza tempPizza;

    @Before
    public void initorder() {
        Map<String, Double> toppingToPrice = new HashMap<String, Double>();
        toppingToPrice.put("Mushrooms", 2.0);
        toppingToPrice.put("Olives", 1.0);
        Order.toppingToPrice = toppingToPrice;

        Map<String, Double> drinkToPrice = new HashMap<String, Double>();
        drinkToPrice.put("Coke", 1.4);
        Order.drinkToPrice = drinkToPrice;

        Map<String, Double> sizeToPrice = new HashMap<String, Double>();
        sizeToPrice.put("Small", 10.4);
        sizeToPrice.put("Medium", 12.4);
        Map<String, Map<String, Double>> pizzaTypeToSizeToPrice = new HashMap<String, Map<String, Double>>();
        pizzaTypeToSizeToPrice.put("Pepperoni", sizeToPrice);
        pizzaTypeToSizeToPrice.put("Vegetarian", sizeToPrice);
        Order.pizzaTypeToSizeToPrice = pizzaTypeToSizeToPrice;

        Pizza.pizzaTypeToRecipe = new HashMap<String, String>();
        Pizza.pizzaTypeToRecipe.put("Pepperoni", "Pepperoni Recipe.");
        pizzaBuilder = new Pizza.PizzaBuilder()
                .setSize("Small")
                .setType("Pepperoni")
                .updateToppings("Mushrooms", 2)
                .updateToppings("Olives", 4);
        pizza = pizzaBuilder.build();
        order.addPizza(pizza);
        order.updateDrink("Coke", 9);
    }

    @Test
    public void testGetTotalPrice(){
        assert order.getTotalPrice() == 31.0;
    }

    @Test
    public void testGetOrderNum(){
        assert order.getOrderNum() instanceof Integer;
    }

    @Test
    public void testGetPizzaList(){
        List<Pizza> pizzaList = order.getPizzaList();
        assertEquals(1, pizzaList.size());
        assertEquals(pizza, pizzaList.get(0));
    }

    @Test
    public void testGetDrinkToQuantity() {
        Map<String, Integer> drinkToQuantity = order.getDrinkToQuantity();
        assertEquals(1, drinkToQuantity.size());
        assert drinkToQuantity.containsKey("Coke");
        assert drinkToQuantity.get("Coke") == 9;

    }

    @Test
    public void testAllPizzaToString() {
        StringBuilder orderContent = new StringBuilder();
        order.allPizzaToString(orderContent);
        assertEquals("    1. Small Pepperoni Pizza with: Mushrooms*2  Olives*4  \n", orderContent.toString());
    }

    @Test
    public void testAllDrinkToString() {
        StringBuilder orderContent = new StringBuilder();
        order.allDrinkToString(orderContent);
        assertEquals("Coke*9  ", orderContent.toString());
    }

    @Test
    public void testToString(){
        Integer orderNum = order.getOrderNum();
        assertEquals("Order Number: #" + orderNum.toString() + "\n" +
                "Pizzas:\n" +
                "    1. Small Pepperoni Pizza with: Mushrooms*2  Olives*4  \n" +
                "Drinks:\n" +
                "    Coke*9  \n" +
                "Total price: $31.0", order.toString());
    }

    @Test
    public void testRemovedDrink(){
        order.updateDrink("Coke", 0);
        assertEquals(0, order.getDrinkToQuantity().size());
    }

    @Test
    public void testRemovePizza(){
        order.updatePizzaByIndex(0, 0, "", 0);
        assertEquals(0, order.getPizzaList().size());
    }

    @Test
    public void testUpdatePizzaSize(){
        order.updatePizzaByIndex(0, 1, "Medium", 0);
        List<Pizza> pizzaList = order.getPizzaList();
        assertEquals(1, pizzaList.size());
        pizza = pizzaList.get(0);
        assertEquals("Medium",pizza.getSize());
    }

    @Test
    public void testUpdatePizzaType(){
        order.updatePizzaByIndex(0, 2, "Vegetarian", 0);
        List<Pizza> pizzaList = order.getPizzaList();
        assertEquals(1, pizzaList.size());
        pizza = pizzaList.get(0);
        assertEquals("Vegetarian",pizza.getType());
    }

    @Test
    public void testUpdatePizzaTopping(){
        order.updatePizzaByIndex(0, 3, "Olives", 6);
        List<Pizza> pizzaList = order.getPizzaList();
        assertEquals(1, pizzaList.size());
        pizza = pizzaList.get(0);
        assert pizza.getToppingToQuantity().containsKey("Olives");
        assert pizza.getToppingToQuantity().get("Olives") == 6;
    }



}
