package logic;

/**
 * Enum for the possible ratings a customer can give.
 */
public enum Rating {

    NO_RATINGYET(0),
    ONESTAR(1),
    TWOSTAR(2),
    THREESTAR(3),
    FOURSTAR(4),
    FIVESTAR(5);

    private int rating;

    Rating(int rating) {
        this.rating = rating;
    }

    public int getValue() {
        return rating;
    }

    public String toString() {
        return rating + " von 5 Punkten";
    }

    public String toStringStars(){
        return "*".repeat(rating);
    }

}
