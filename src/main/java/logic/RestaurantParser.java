package logic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class RestaurantParser {

    private final String url = "https://www.speisekarte.de/%s/restaurants";

    public RestaurantParser() {
    }

    public void fetchRestaurants(String city) {
        try {
            String tmp = String.format(url, city);
            Document doc = Jsoup.connect(tmp).get();
            Element elem = doc.getElementById("searchFilterResult");
            String data = elem.html();
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RestaurantParser parser = new RestaurantParser();
        parser.fetchRestaurants("München");
    }
}
