package source;
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

    public User getAuthor(){
        return UserList.getInstance(null).getUser(authorID);
    } 
       
    public Date getDateAdded(){
        return dateAdded;
    }
    
    public String getReview(){
        return review;
    }
    
    public float getRating(){
        return rating;
    }

    /*
     * user123
     * I dont like this course
     * 1.2
     * 1/3/2012
     */
    public String toString() {
        return 
        UserList.getInstance(null).getUser(authorID).getUsername()+"\n"+
        review+"\n"+
        rating+"\n"+
        dateAdded+"\n";
    }
}