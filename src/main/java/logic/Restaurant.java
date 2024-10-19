package logic;

import org.postgresql.shaded.com.ongres.stringprep.Tables;

import java.math.BigDecimal;
import java.util.*;

public class Restaurant {

    private String name;
    private UUID id;
    private Pricing priceCategory;
    private String cuisine;
    private String city;
    private List<Table> tables;
    private int maxVisitors;
    private String fullAddress;
    private String link;

    public Restaurant() {}

    //every fetched restaurant has to be instantiated once to create a random number of tables for 2 - 6 persons each
    public Restaurant(String name, String cuisine, String city, String address, String link) {
        this.name = name;
        this.id = UUID.randomUUID();
        this.priceCategory = Pricing.random();
        this.cuisine = cuisine;
        this.city = city;
        this.tables = new ArrayList<>();
        Random random = new Random();
        int numOfTables = random.nextInt(5, 20);
        for (int i = 1; i <= numOfTables; i++) {
            tables.add(new Table(i));
        }
        this.fullAddress = address;
        this.link = link;
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
        return cuisine;
    }

    public String getCity() {
        return city;
    }

    public List<Table> getTables() {
        return tables;
    }

    public int getMaxVisitors() {
        return maxVisitors;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getLink() {
        return link;
    }

    public int[] getAllTables() {
        int two = 0, four = 0, six = 0;
        for (Table t : tables) {
            switch(t.getNumberOfPeople()) {
                case 2 -> two++;
                case 4 -> four++;
                case 6 -> six++;
            }
        }
        return new int[] {two, four, six};
    }

    /*TODO:
    *  - remove DAY and TimeSlot, no need to model -> limit options directly in the frontend
    *    e.g. disable minutes or set to 30-min steps
    *  - reserve table: add to database (done)
    *                   disable selection in DateTimePicker
    *                   sent out confirmation for reservation
    *  - cancel reservation: remove from database (done)
    *                        allow selection in DateTimePicker
    *                        sent out confirmation for cancellation
    *  -> all dates and times, that can be selected, are assumed to be available
    * */

}
