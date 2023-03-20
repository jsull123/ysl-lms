package source;

import java.util.UUID;
import java.util.ArrayList;

public class Course{
    private UUID courseID;
    private String title;
    private String language;
    private float rating;
    private String description;
    private UUID authorID;
    private ArrayList<Review> reviews;
    private ArrayList<Comment> comments;
    private ArrayList<Module> modules;
    
    public Course(UUID courseID, String title,
    String language, float rating, String description,
    UUID authorID, ArrayList<Review> reviews,
    ArrayList<Comment> comments, ArrayList<Module> modules) {
        
        this.courseID = courseID;
        this.title = title;
        this.language = language;
        this.rating = rating;
        this.description = description;
        this.authorID = authorID;
        this.reviews = reviews;
        this.comments = comments;
        this.modules = modules;
    }
    
    public int numReviews() {
        return reviews.size();
    }
    
    public int numComments() {
        return comments.size();
    } 
    
    public int numModules() {
        return modules.size();
    }
    
    public Review getReview(int index) {      
        return reviews.get(index);
    }
    public Comment getComment(int index) {
        return this.comments.get(index);
    }
    
    public Module getModule(int index) { 
        return this.modules.get(index);
    }
    
    public void addReview(Review review) {
        this.reviews.add(review);
    }
    
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
    
    public UUID getCourseID() {
        return this.courseID;
    }
}

 


