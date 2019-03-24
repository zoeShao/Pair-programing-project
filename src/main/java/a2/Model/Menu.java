package a2.Model;

public class Menu {
    public static String getFullMenu() {
        StringBuilder fullMenu = new StringBuilder();
        fullMenu.append("<Full menu>\n");
        fullMenu.append("- Pizza Price:\n\n");
        for (String type: Order.pizzaTypeToSizeToPrice.keySet()) {
            fullMenu.append("    ")
                    .append(type)
                    .append(" Pizza\n");
            for (String size: Order.pizzaTypeToSizeToPrice.get(type).keySet()) {
                String sizeAndType = size + " " + type;
                fullMenu.append("        ")
                        .append(size)
                        .append("  ====================================  ")
                        .append(Menu.getPizzaPrice(sizeAndType))
                        .append("\n");
            }
            fullMenu.append("\n");
        }
        fullMenu.append("- Topping Price:\n\n");
        for (String topping: Order.toppingToPrice.keySet()) {
            fullMenu.append("    ")
                    .append(topping)
                    .append("  ====================================  ")
                    .append(Menu.getToppingPrice(topping))
                    .append("\n");
        }
        fullMenu.append("\n");
        fullMenu.append("- Drink Price:\n\n");
        for (String name: Order.drinkToPrice.keySet()) {
            fullMenu.append("    ")
                    .append(name)
                    .append("  ====================================  ")
                    .append(Menu.getDrinkPrice(name))
                    .append("\n");
        }
        return fullMenu.toString();
    }

    public static String getPizzaPrice(String sizeAndType) {
        String[] parts = sizeAndType.split(" ");
        return "$" + Order.pizzaTypeToSizeToPrice.get(parts[1]).get(parts[0]);
    }

    public static String getToppingPrice(String topping) {
        return "$" + Order.toppingToPrice.get(topping);
    }

    public static String getDrinkPrice(String name) {
        return "$" + Order.drinkToPrice.get(name);
    }
}
