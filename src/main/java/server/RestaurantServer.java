package server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class RestaurantServer {

    //in order to test the view of restaurants
    public static void main(String[] args) {
        SpringApplication.run(RestaurantServer.class, args);
    }
}
