package server;

import logic.Restaurant;
import logic.RestaurantParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private DatabaseConnection connection;
    private RestaurantParser parser;

    @Autowired
    public RestaurantService() {
        this.connection = DatabaseConnection.getInstance();
        this.parser = new RestaurantParser();
    }

    public List<Restaurant> searchRestaurants(String city) {
        if (!connection.hasQuery("city", city)) {
            parser.fetchRestaurantsByCity(city);
        }
        return connection.getRestaurantByCity(city);
    }

    public Restaurant viewSingleRestaurant(String id) {
        return connection.getSingleRestaurant(id);
    }
}
