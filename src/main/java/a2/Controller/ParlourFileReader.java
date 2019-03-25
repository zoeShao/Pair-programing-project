package a2.Controller;

import a2.Model.Order;
import a2.Model.Pizza;
import a2.PizzaParlour;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ParlourFileReader {

  public static void readPizzaRecipe(String fileName) {
    Map<String, String> typeToRecipe = new HashMap<String, String>();
    String line;
    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String pizzaType = "";
      while ((line = bufferedReader.readLine()) != null) {
        if (line.charAt(0) == '#') {
          pizzaType = line.substring(1, line.length());
          typeToRecipe.put(pizzaType, "");
        } else {
          String recipeLine = typeToRecipe.get(pizzaType) + line + "\n";
          typeToRecipe.put(pizzaType, recipeLine);
        }
      }
      bufferedReader.close();
    } catch (FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    } catch (IOException ex) {
      System.out.println("Error reading file '" + fileName + "'");
    }
    Pizza.pizzaTypeToRecipe = typeToRecipe;
    PizzaParlour.allTypes = typeToRecipe.keySet();
  }

  public static void readPizzaTypeSizePrice(String fileName) {

    Map<String, Map<String, Double>> pizzaTypeToSizeToPrice =
        new HashMap<String, Map<String, Double>>();
    try {
      FileReader fileReader = new FileReader(fileName);
      CSVReader reader = new CSVReader(fileReader);
      String[] nextLine;
      String[] sizes;
      if ((nextLine = reader.readNext()) != null) {
        sizes = nextLine;
        updatePizzaSizes(sizes);
        while ((nextLine = reader.readNext()) != null) {
          pizzaTypeToSizeToPrice.put(nextLine[0], sizeToPrice(sizes, nextLine));
        }
      }
      reader.close();
    } catch (FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    } catch (IOException ex) {
      System.out.println("Error reading file '" + fileName + "'");
    }
    Order.pizzaTypeToSizeToPrice = pizzaTypeToSizeToPrice;
  }

  public static void readDrinkPrice(String fileName) {
    Map<String, Double> drinkToPrice = new HashMap<String, Double>();
    try {
      FileReader fileReader = new FileReader(fileName);
      CSVReader reader = new CSVReader(fileReader);
      String[] nextLine;
      if ((nextLine = reader.readNext()) != null) {
        while ((nextLine = reader.readNext()) != null) {
          drinkToPrice.put(nextLine[0], Double.parseDouble(nextLine[1]));
        }
      }
      reader.close();
    } catch (FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    } catch (IOException ex) {
      System.out.println("Error reading file '" + fileName + "'");
    }
    Order.drinkToPrice = drinkToPrice;
    PizzaParlour.allDrinks = drinkToPrice.keySet();
  }

  public static void readToppingPrice(String fileName) {
    Map<String, Double> toppingToPrice = new HashMap<String, Double>();

    try {
      FileReader fileReader = new FileReader(fileName);
      CSVReader reader = new CSVReader(fileReader);
      String[] nextLine;
      if ((nextLine = reader.readNext()) != null) {
        while ((nextLine = reader.readNext()) != null) {
          toppingToPrice.put(nextLine[0], Double.parseDouble(nextLine[1]));
        }
      }
      reader.close();
    } catch (FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    } catch (IOException ex) {
      System.out.println("Error reading file '" + fileName + "'");
    }
    Order.toppingToPrice = toppingToPrice;
    PizzaParlour.allToppings = toppingToPrice.keySet();
  }

  private static Map<String, Double> sizeToPrice(String[] sizeList, String[] priceList) {
    Map<String, Double> sizeToPrice = new HashMap<String, Double>();
    for (int i = 1; i < sizeList.length; i++) {
      sizeToPrice.put(sizeList[i], Double.parseDouble(priceList[i]));
    }
    return sizeToPrice;
  }

  private static void updatePizzaSizes(String[] sizes) {
    Set<String> allSizes = new HashSet<String>();
    allSizes.addAll(Arrays.asList(sizes).subList(1, sizes.length));
    PizzaParlour.allSizes = allSizes;
  }
}
