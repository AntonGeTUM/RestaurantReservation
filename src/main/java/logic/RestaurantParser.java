package logic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import server.DatabaseConnection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestaurantParser {

    private final String url = "https://www.speisekarte.de/%s/restaurants%s";
    private final String[] pages = {"", "?page=2", "?page=3"};
    private final DatabaseConnection connection = DatabaseConnection.getInstance();

    public RestaurantParser() {
    }

    public void fetchRestaurantsByCity(String city) {
        if (connection.hasQuery("city", city)) return;
        try {
            List<String> names = new ArrayList<>();
            List<String> links = new ArrayList<>();
            for (int i = 0; i < pages.length; i++) {
                String tmp = String.format(url, city, pages[i]);
                Document doc = Jsoup.connect(tmp).get();
                var res = doc.body().getElementsByAttributeValueContaining("id", "restaurant-result-title");
                res.stream().map(e -> e.select("a"))
                        .forEach(e -> {
                            links.add(e.attr("href"));
                            names.add(e.text());
                        });
            }
            List<String> addresses = new ArrayList<>();
            List<List<String>> cuisines = new ArrayList<>();
            List<String> phoneNumbers = new ArrayList<>();
            List<String> websites = new ArrayList<>();
            for (String link : links) {
                Document curRes = Jsoup.connect(link).get();

                addresses.add(curRes.getElementById("detail-map").select("p").text());

                List<String> cuisineTypes = new ArrayList<>();
                var elems = curRes.getElementById("detail-kitchen-types").select("a");
                for (Element e : elems) cuisineTypes.add(e.text());
                cuisines.add(cuisineTypes);

                phoneNumbers.add(curRes.getElementById("detail-phone").attr("href"));

                if (curRes.getElementById("detail-website") != null) {
                    websites.add(curRes.getElementById("detail-website").attr("href"));
                } else {
                    websites.add("no website available");
                }
            }
            for (int i = 0; i < websites.size(); i++) {
                Restaurant res = new Restaurant(names.get(i), cuisines.get(i), city, addresses.get(i), websites.get(i), phoneNumbers.get(i));
                connection.insertRestaurant(res.getId().toString(), res.getName(), res.getCuisine(), res.getCity(),
                        res.getPriceCategory().name(), res.getFullAddress(), res.getLink(), res.getPhone());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RestaurantParser parser = new RestaurantParser();
        parser.fetchRestaurantsByCity("München");
    }
}
