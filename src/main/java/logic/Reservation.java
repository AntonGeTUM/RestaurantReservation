package logic;

import server.DatabaseConnection;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;

public class Reservation {

    private UUID uuid;
    private String restaurantID;
    private LocalDateTime date;
    private int numberPeople;

    public Reservation(String restaurantID, LocalDateTime date, int numberPeople) {
        this.uuid = UUID.randomUUID();
        this.restaurantID = restaurantID;
        this.date = date;
        this.numberPeople = numberPeople;
    }

    public String toString() {
        return "Reservierung für einen Tisch mit " + numberPeople + " Personen am " + date.toLocalDate().toString()
                + " um " + date.toLocalTime().toString() + "h.";
    }

    public String getDateString() {
        return this.date.toLocalDate().toString();
    }

    public String getTimeString() {
        return this.date.toLocalTime().toString();
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getNumberPeople() {
        return numberPeople;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

}
