package server;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import logic.Pricing;
import logic.Rating;
import logic.Restaurant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
            , String address, String link, String phone) {
        final String query = "insert into restaurant(id, name, cuisine, city, price_cat, address, link, phone_number)" +
                " values(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, cuisine);
            statement.setString(4, city);
            statement.setString(5, priceCat);
            statement.setString(6, address);
            statement.setString(7, link);
            statement.setString(8, phone);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Restaurant> getRestaurantByCity(String city) {
        final String query = "select * from restaurant where city = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, city);
            ResultSet set = statement.executeQuery();
            List<Restaurant> res = new ArrayList<>();
            while(set.next()) {
                List<String> cuisine = Arrays.asList(set.getString(3).split(", "));
                Restaurant r = new Restaurant(set.getString(1), set.getString(2), cuisine,
                        set.getString(4), set.getString(5),
                        set.getString(6), set.getString(7), set.getString(8));
                res.add(r);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Restaurant getSingleRestaurant(String id) {
        final String query = "select * from restaurant where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            List<String> cuisine = Arrays.asList(set.getString(3).split(", "));
            return new Restaurant(set.getString(1), set.getString(2), cuisine,
                    set.getString(4), set.getString(5),
                    set.getString(6), set.getString(7), set.getString(8));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public int[] getReservationsGivenDateTime(String restaurantID, String date, String begin) {
        final String query = "select num_people from reservation where restau_id = ? AND date = ? AND begin = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, restaurantID);
            statement.setString(2, date);
            statement.setString(3, begin);
            ResultSet set = statement.executeQuery();
            int rows = set.last() ? set.getRow() : 0;
            if (rows == 0) return new int[]{};
            set.first();
            int[] res = new int[rows];
            for (int i = 0; i < rows; i++) {
                res[i] = set.getInt(1);
                set.next();
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
