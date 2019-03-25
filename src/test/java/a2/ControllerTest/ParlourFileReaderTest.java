package a2.ControllerTest;

import a2.Controller.ParlourFileReader;
import a2.Model.Order;
import a2.Model.Pizza;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ParlourFileReaderTest {
  @Test
  public void testReadPizzaRecipe() throws Exception {
    ParlourFileReader.readPizzaTypeSizePrice("src/main/Files/TestPizzaTypeSizePrice.csv");
    Map<String, Map<String, Double>> myPrice = new HashMap<String, Map<String, Double>>();
    Map<String, Double> mySize = new HashMap<String, Double>();
    mySize.put("Small", 10.0);
    mySize.put("Medium", 13.0);
    mySize.put("Large", 19.7);
    myPrice.put("Pepperoni", mySize);
    assertEquals(myPrice, Order.pizzaTypeToSizeToPrice);
  }

  @Test
  public void tesReadPizzaRecipe() throws Exception {
    ParlourFileReader.readPizzaRecipe("src/main/Files/TestRecipes.txt");
    Map<String, String> myRecipe = new HashMap<String, String>();
    myRecipe.put("Pepperoni", "Test Recipe\n");
    assertEquals(myRecipe, Pizza.pizzaTypeToRecipe);
  }

  @Test
  public void testReadDrinkPrice() throws Exception {
    ParlourFileReader.readDrinkPrice("src/main/Files/TestDrinkPrice.csv");
    Map<String, Double> myDrink = new HashMap<String, Double>();
    myDrink.put("Coke", 10.0);
    assertEquals(myDrink, Order.drinkToPrice);
  }

  @Test
  public void testReadToppingPrice() throws Exception {
    ParlourFileReader.readToppingPrice("src/main/Files/TestToppingPrice.csv");
    Map<String, Double> myTopping = new HashMap<String, Double>();
    myTopping.put("Olives", 1.2);
    assertEquals(myTopping, Order.toppingToPrice);
  }
}
