package server;

import logic.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestaurantController {

    @Autowired
    private RestaurantService service;

    @GetMapping("/")
    public String home() {
        return "Restaurant Reservation Service";
    }

    //TODO
    //post review
    //reserve table

    //load restaurants
    @GetMapping("restaurants/{city}")
    public ResponseEntity<List<Restaurant>> loadRestaurants(@PathVariable("city") String city) {
        return ResponseEntity.ok(service.searchRestaurants(city));
    }

    //select restaurant
    @GetMapping("restaurants/{city}/{id}")
    public ResponseEntity<Restaurant> viewSingleRestaurant(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.viewSingleRestaurant(id));
    }
    //cancel reservation
    //show reservations
}
