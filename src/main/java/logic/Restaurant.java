package logic;

import java.math.BigDecimal;
import java.util.*;

public class Restaurant {

    private String name;
    private UUID id;
    private Pricing priceCategory;
    private String cuisine;
    private String city;
    private List<Review> reviews;
    private BigDecimal rating;
    private List<Table> tables;
    private HashMap<String, Reservation> reservations;
    private int maxVisitors;
    private String fullAddress;
    private String link;

    public Restaurant() {}

    public Restaurant(String name, String cuisine, String city, String link) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.priceCategory = Pricing.random();
        this.cuisine = cuisine;
        this.city = city;
        this.reviews = new ArrayList<>();
        this.rating = BigDecimal.ZERO;
        this.tables = new ArrayList<>();
        Random random = new Random();
        int numOfTables = random.nextInt(5, 20);
        for (int i = 1; i <= numOfTables; i++) {
            tables.add(new Table(i));
        }
        this.reservations = new HashMap<String, Reservation>();
        this.fullAddress = "";
        this.link = link;
    }

    //TODO:

    //create schedule, i.e. available reservation slots

    //get maximum number of visitors

    //add review (recalculate rating)

    //calculate average rating

    //check for reservation slot for a given date

    //return available tables

    //reserve table: remove from available slots, add to table

    //remove reservation, add back to available
}
