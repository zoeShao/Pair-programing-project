package a2.Controller;

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
}
