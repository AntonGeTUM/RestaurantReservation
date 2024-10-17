package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})

public class RestaurantController {

    @Autowired
    private RestaurantService service;

    //TODO
    //post review
    //reserve table
    //load restaurants
    //select restaurant
    //cancel reservation
    //show reservations
}
