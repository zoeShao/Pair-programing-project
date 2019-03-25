package a2.ModelTest;

import a2.Controller.ParlourFileReader;
import a2.Model.Menu;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MenuTest {
  @Before
  public void init() {
    ParlourFileReader.readPizzaTypeSizePrice("src/main/Files/TestPizzaTypeSizePrice.csv");
    ParlourFileReader.readPizzaRecipe("src/main/Files/TestRecipes.txt");
    ParlourFileReader.readDrinkPrice("src/main/Files/TestDrinkPrice.csv");
    ParlourFileReader.readToppingPrice("src/main/Files/TestToppingPrice.csv");
  }

  @Test
  public void testFullMenu() {
    assertEquals(
        "<Full menu>\n"
            + "- Pizza Price:\n"
            + "\n"
            + "    Pepperoni Pizza\n"
            + "        Small  =====================================  $10.0\n"
            + "        Medium  =====================================  $13.0\n"
            + "        Large  =====================================  $19.7\n"
            + "\n"
            + "- Topping Price:\n"
            + "\n"
            + "    Olives  =====================================  $1.2\n"
            + "\n"
            + "- Drink Price:\n"
            + "\n"
            + "    Coke  =====================================  $10.0\n",
        Menu.getFullMenu());
  }
}
