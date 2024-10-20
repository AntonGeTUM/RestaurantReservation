package server;

import logic.Rating;
import logic.Reservation;
import logic.Restaurant;
import logic.Review;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DatabaseConnection {

    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private Connection connection;
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        try {
            String[] access = readUserAndPW();
            if (access == null) {
                System.out.println("Could not retrieve login data.");
                return;
            }
            connection = DriverManager.getConnection(url, access[0], access[1]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void insertRestaurant(String id, String name, String cuisine, String city, String priceCat
    , String address, int table2, int table4, int table6, String link, String phone) {
        final String query = "insert into restaurant(id, name, cuisine, city, price_cat, address, table_2, table_4, table_6, link, phone_number)" +
                " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, cuisine);
            statement.setString(4, city);
            statement.setString(5, priceCat);
            statement.setString(6, address);
            statement.setInt(7, table2);
            statement.setInt(8, table4);
            statement.setInt(9, table6);
            statement.setString(10, link);
            statement.setString(11, phone);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRating(String id, Rating rating, String name, String restaurantID, String review) {
        final String query = "insert into review(id, rating, name_reviewer, restau_id, review_text) " +
                "values(?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setInt(2, rating.getValue());
            statement.setString(3, name);
            statement.setString(4, restaurantID);
            statement.setString(5, review);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertReservation(String id, String restaurantID, String date, String begin, int numberPeople) {
        final String query = "insert into reservation(id, restau_id, date, begin, num_people)" +
                "values(?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, restaurantID);
            statement.setString(3, date);
            statement.setString(4, begin);
            statement.setInt(5, numberPeople);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getAvgRating(String uuid) {
        final String query = "select avg(rating) from review where restau_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, uuid);
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getBigDecimal(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    public boolean hasQuery(String type, String id) {
        final String query = hasQueryString(type);
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            return set.getBoolean(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private String hasQueryString(String type) {
        return switch (type) {
            case "reservation" -> "select exists(select 1 from reservation where id = ?)";
            case "review" -> "select exists(select 1 from review where restau_id = ?)";
            case "city" -> "select exists(select 1 from restaurant where city = ?)";
            default -> "";
        };
    }

    public void removeReservation(String id) {
        if (!hasQuery("reservation", id)) return;
        final String query = "delete from reservation where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String[] readUserAndPW() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/postgres.txt"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("-");
            }
            return sb.toString().split("-");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
