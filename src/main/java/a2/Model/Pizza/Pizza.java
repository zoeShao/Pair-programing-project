package a2.Model.Pizza;

import java.util.List;

public interface Pizza {
    String getSize();
    List<String> getToppings();
    void setSize(String size);
    void setToppings(List<String> newToppings);
}
