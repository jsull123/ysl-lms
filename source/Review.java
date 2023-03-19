package source;

import java.util.Date;
import java.util.UUID;

public class Review { 

    private UUID authorID;
    private float rating;
    private String review;
    private Date dateAdded;

    public Review(UUID authorID, float rating, String review, Date dateAdded) {
        this.authorID = authorID;
        this.rating = rating;
        this.review = review;
        this.dateAdded = dateAdded;
    }

    public String toString() {
        return "";
    }
}