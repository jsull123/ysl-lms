package source;
import java.util.UUID;

public class Review { 
    private UUID authorID;
    private float rating;
    private String review;
    private Date dateAdded;
    /**
     * The constructor for the Review object
     * @param UUID authorID = the unique ID of the author
     * @param String review = The review of the user
     * @param Date dateAdded = The date the review was made
     */
    public Review(UUID authorID, float rating, String review, Date dateAdded) {
        this.authorID = authorID;
        this.rating = rating;
        this.review = review;
        this.dateAdded = dateAdded;
    }
    /**
     * @return the Author of a review
     */
    public User getAuthor(){
        return UserList.getInstance(null).getUser(authorID);
    } 
       /**
        * @return the date the review was left
        */
    public Date getDateAdded(){
        return dateAdded;
    }
    /**
     * @return the review that the user wrote
     */
    public String getReview(){
        return review;
    }
    /**
     * @return the rating the user left
     */
    public float getRating(){
        return rating;
    }
    /**
     * @return a String representation of the review
     */
    public String toString() {
        return 
        UserList.getInstance(null).getUser(authorID).getUsername()+"\n"+
        review+"\n"+
        rating+"\n"+
        dateAdded+"\n";
    }
}