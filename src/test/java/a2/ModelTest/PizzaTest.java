package a2.ModelTest;

import a2.Model.Pizza;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class PizzaTest {
    private Pizza pizza;
    private Pizza.PizzaBuilder pizzaBuilder;
    private Pizza tempPizza;

    @Before
    public void initPizza() {
        Pizza.pizzaTypeToRecipe = new HashMap<String, String>();
        Pizza.pizzaTypeToRecipe.put("Pepperoni", "Pepperoni Recipe.");
        pizzaBuilder = new Pizza.PizzaBuilder()
                .setSize("Small")
                .setType("Pepperoni")
                .updateToppings("Mushrooms", 2)
                .updateToppings("Olives", 4);
        pizza = pizzaBuilder.build();
    }

    @Test
    public void testToString(){
        assertEquals("Small Pepperoni Pizza with: Mushrooms*2  Olives*4  ", pizza.toString());
    }

    @Test
    public void testGetRecipe(){
        assertEquals("Pepperoni Recipe.", pizza.getRecipe());
    }

    @Test
    public void testGetSize() {
        assertEquals("Small", pizza.getSize());
    }

    @Test
    public void testGetType() {
        assertEquals("Pepperoni", pizza.getType());
    }

    @Test
    public void testGetToppings() {
        Map<String, Integer> toppingToQuantity = pizza.getToppingToQuantity();
        assertEquals(2, toppingToQuantity.size());
        assert toppingToQuantity.containsKey("Mushrooms");
        int quantity = toppingToQuantity.get("Mushrooms");
        assertEquals(2, quantity);
        assert toppingToQuantity.containsKey("Olives");
        quantity = toppingToQuantity.get("Olives");
        assertEquals(4, quantity);
    }

    @Test
    public void testDeleteToppings(){
        pizzaBuilder.updateToppings("Mushrooms", 0);
        tempPizza = pizzaBuilder.build();
        Map<String, Integer> toppingToQuantity = tempPizza.getToppingToQuantity();
        assertEquals(1, toppingToQuantity.size());
        assert !toppingToQuantity.containsKey("Mushrooms");
        assert toppingToQuantity.containsKey("Olives");
        int quantity = toppingToQuantity.get("Olives");
        assertEquals(4, quantity);
    }

    @Test
    public void testSetToppings(){
        pizzaBuilder.updateToppings("Mushrooms", 0);
        tempPizza = pizzaBuilder.build();
        pizzaBuilder.setToppings(tempPizza.getToppingToQuantity());
        pizza = pizzaBuilder.build();
        Map<String, Integer> toppingToQuantity = pizza.getToppingToQuantity();
        assertEquals(1, toppingToQuantity.size());
        assert !toppingToQuantity.containsKey("Mushrooms");
        assert toppingToQuantity.containsKey("Olives");
        int quantity = toppingToQuantity.get("Olives");
        assertEquals(4, quantity);
    }

}
