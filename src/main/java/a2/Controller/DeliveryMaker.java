package a2.Controller;


import a2.Model.Order;
import com.opencsv.CSVWriter;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DeliveryMaker {
    private static Map<String, String> deliveryDetails = new HashMap<String, String>();


    public static void setDeliveryDetails(Order myOrder, String userAddress) {
        deliveryDetails.put("Address", userAddress);
        deliveryDetails.put("Order Details", myOrder.toString());
        deliveryDetails.put("Order Number", myOrder.getOrderNum().toString());
    }

    public static Map<String, String> inHouseDelivery() {
        return deliveryDetails;
    }

    private static void uberEatsDelivery() {
        JSONObject uberEatsDetails = new JSONObject();
        uberEatsDetails.put("Address", deliveryDetails.get("Address"));
        uberEatsDetails.put("Order Details", deliveryDetails.get("Order Details"));
        uberEatsDetails.put("Order Number", deliveryDetails.get("Order Number"));
        try {
            FileWriter file = new FileWriter("src/main/Files/UberEatsDeliveryDetails.json");
            file.write(uberEatsDetails.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void foodoraDelivery() {
        try {
            FileWriter outputfile = new FileWriter("src/main/Files/FoodoraDeliveryDetails.csv");
            CSVWriter writer = new CSVWriter(outputfile);
            String[] AddressData = { "Address", deliveryDetails.get("Address") };
            writer.writeNext(AddressData);
            String[] detailData = { "Order Details", deliveryDetails.get("Order Details") };
            writer.writeNext(detailData);
            String[] orderNumData = { "Order Number", deliveryDetails.get("Order Number") };
            writer.writeNext(orderNumData);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeDelivery(String choice) {
        String inHouseConfirmMessage = "[Notice]: You have completed delivery part, Please wait patiently.\n" +
                "Our delivery person will arrive to your place ASAP!";
        String uberEatsConfirmMessage = "[Notice]: Done! We have informed Uber Eats, Please wait patiently.";
        String foodoraConfirmMessage = "[Notice]: Done! We have informed Foodora, Please wait patiently.";
        if (choice.equals("InHouse")) {
            System.out.println(inHouseConfirmMessage);
        } else if (choice.equals("UberEats")) {
            uberEatsDelivery();
            System.out.println(uberEatsConfirmMessage);
        } else if (choice.equals("Foodora")) {
            foodoraDelivery();
            System.out.println(foodoraConfirmMessage);
        }
    }
}
