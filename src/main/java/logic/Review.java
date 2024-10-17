package logic;

/**
 *
 */
public class Review {

    private String name;
    private Rating rating;
    private String review;

    public Review() {}

    /**
     * A review object consists of the name of the review, a rating and a text.
     * @param name the name of the reviewer
     * @param rating the rating as String (see Enum Rating)
     * @param review the content of the review
     */
    public Review(String name, Rating rating, String review) {
        this.name = name;
        this.rating = rating;
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Bewertung von " + name + " mit " + rating + ":\nInhalt: " + review;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Rating getRating() {
        return rating;
    }

    public int getRatingValue() {
        return rating.getValue();
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

}
