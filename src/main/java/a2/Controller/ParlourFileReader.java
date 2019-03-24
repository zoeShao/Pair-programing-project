package a2.Controller;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParlourFileReader {
    public static Map<String, String> readPizzaRecipe(String fileName){
        Map<String, String> typeToRecipe = new HashMap<String, String>();
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String pizzaType = "";

            while((line = bufferedReader.readLine()) != null) {
                if (line.charAt(0) == '#') {
                    // pizza type indicator
                    pizzaType = line.substring(1, line.length());
                    typeToRecipe.put(pizzaType, "");
                } else {
                    // recipe for a pizza type
                    String recipeLine = typeToRecipe.get(pizzaType) + line + "\n";
                    typeToRecipe.put(pizzaType, recipeLine);
                }
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        return typeToRecipe;
    }

    public static Map<String, Map<String, Double>> readPizzaTypeSizePrice(String fileName){

        Map<String, Map<String, Double>> pizzaTypeToSizeToPrice = new HashMap<String, Map<String, Double>>();

        try {
            FileReader fileReader = new FileReader(fileName);
            CSVReader reader = new CSVReader(fileReader);
            String [] nextLine;
            String [] sizes;
            if ((nextLine = reader.readNext()) != null) {
                sizes = nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    pizzaTypeToSizeToPrice.put(nextLine[0], sizeToPrice(sizes, nextLine));
                }
            }
            reader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        return pizzaTypeToSizeToPrice;
    }

    public static Map<String, Double> readDrinkPrice(String fileName) {
        Map<String, Double> drinkToPrice = new HashMap<String, Double>();

        try {
            FileReader fileReader = new FileReader(fileName);
            CSVReader reader = new CSVReader(fileReader);
            String [] nextLine;
            if ((nextLine = reader.readNext()) != null) {
                while ((nextLine = reader.readNext()) != null) {
                    drinkToPrice.put(nextLine[0], Double.parseDouble(nextLine[1]));
                }
            }
            reader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        return drinkToPrice;
    }

    public static Map<String, Double> readToppingPrice(String fileName) {
        Map<String, Double> toppingToPrice = new HashMap<String, Double>();

        try {
            FileReader fileReader = new FileReader(fileName);
            CSVReader reader = new CSVReader(fileReader);
            String [] nextLine;
            if ((nextLine = reader.readNext()) != null) {
                while ((nextLine = reader.readNext()) != null) {
                    toppingToPrice.put(nextLine[0], Double.parseDouble(nextLine[1]));
                }
            }
            reader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        return toppingToPrice;
    }

    private static Map<String, Double> sizeToPrice(String [] sizeList, String [] priceList) {
        Map<String, Double> sizeToPrice = new HashMap<String, Double>();
        for (int i = 1; i < sizeList.length; i++) {
            sizeToPrice.put(sizeList[i], Double.parseDouble(priceList[i]));
        }
        return sizeToPrice;
    }
}
