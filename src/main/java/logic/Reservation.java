package logic;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;

public class Reservation {

    private String uuid;
    private LocalDateTime date;
    private int numberPeople;

    public Reservation() {}

    public Reservation(String uuid, LocalDateTime date, int numberPeople) {
        this.uuid = uuid;
        this.date = date;
        this.numberPeople = numberPeople;
    }

    public String toString() {
        return "Reservierung für einen Tisch mit " + numberPeople + " Personen am" + date.toString();
    }

    public String getUuid() {
        return uuid;
    }

    public int getNumberPeople() {
        return numberPeople;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String encodeReservation() {
        return Base64.getEncoder().encodeToString(date.toString().getBytes(StandardCharsets.UTF_8));
    }

}
