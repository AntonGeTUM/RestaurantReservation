package logic;

import org.postgresql.shaded.com.ongres.stringprep.Tables;

import java.math.BigDecimal;
import java.util.*;

public class Restaurant {

    private String name;
    private UUID id;
    private Pricing priceCategory;
    private List<String> cuisine;
    private String city;
    private String fullAddress;
    private String link;
    private String phone;

    public Restaurant() {}

    //every fetched restaurant has to be instantiated once to create a random number of tables for 2 - 6 persons each
    public Restaurant(String name, List<String> cuisine, String city, String address, String link, String phone) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.priceCategory = Pricing.random(cuisine.contains("Gourmet"));
        this.cuisine = cuisine;
        this.city = city;
        this.fullAddress = address;
        this.link = link;
        this.phone = phone;
    }

    public Restaurant(String uuid, String name, List<String> cuisine, String city, String priceCat, String address,
                      String link, String phone) {
        this.id = UUID.fromString(uuid);
        this.name = name;
        this.cuisine = cuisine;
        this.city = city;
        this.priceCategory = Pricing.fromString(priceCat);
        this.fullAddress = address;
        this.link = link;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public Pricing getPriceCategory() {
        return priceCategory;
    }

    public String getCuisine() {
        return String.join(", ", cuisine);
    }

    public String getCity() {
        return city;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getLink() {
        return link;
    }

    public String getPhone() {
        return phone;
    }

    /*TODO:
    *  - remove DAY and TimeSlot, no need to model -> limit options directly in the frontend
    *    e.g. disable minutes or set to 30-min steps
    *  - reserve table: add to database (done)
    *                   sent out confirmation for reservation
    *  - cancel reservation: remove from database (done)
    *                        sent out confirmation for cancellation
    *  -> all dates and times, that can be selected, are assumed to be available
    * */
}
