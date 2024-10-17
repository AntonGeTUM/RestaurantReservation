package logic;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Restaurant {

    private String name;
    private UUID id;
    private Pricing priceCategory;
    private String cuisine;
    private String city;
    private List<Review> reviews;
    private BigDecimal rating;
}
