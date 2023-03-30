package source;

import java.util.UUID;
import java.util.ArrayList;

public class Course{
    private UUID courseID;
    private String title;
    private Language language;
    private String description;
    private UUID authorID;
    private ArrayList<Review> reviews;
    private ArrayList<Comment> comments;
    private ArrayList<Module> modules;
    /**
     * Constructor for a Course object
     * @param UUID courseID = The unique ID for a course
     * @param String title = The title of a course
     * @param Language language = The langauage that the course teaches
     * @param String description = A description of the course and its contents
     * @param UUID authorID = The unique ID of the auhtor of the course
     * @param ArrayList<Module> modules = The modules in a given course 
     */
    public Course(UUID courseID, String title,
    Language language, String description,
    UUID authorID, ArrayList<Review> reviews,
    ArrayList<Comment> comments, ArrayList<Module> modules) { 
        this.courseID = courseID;
        this.title = title;
        this.language = language;
        this.description = description;
        this.authorID = authorID;
        this.reviews = reviews;
        this.comments = comments;
        this.modules = modules;
    }
    /**
     * @return The title of the course
     */
    public String toString(){
        return title;
    }
    /**
     * Sets the title of the course to a specified string
     * @param String title = The new title
     */
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * Sets the language to a specified language
     * @param Language language = The new language
     */
    public void setLanguage(Language language){
        this.language = language;
    }
    /**
     * Sets the description to a specified string
     * @param String description = The new description
     */
    public void setDescription(String description){
        this.description = description;
    }
     /**
      * @return The author's unique ID
      */
    public UUID getAuthorID(){
        return authorID;
    }
    /**
     * @return The description of the course
     */
    public String getDescription(){
        return description;
    }
    /**
     * @return List of the modules in the course
     */
    public ArrayList<Module> getAllModules(){
        return modules;
    }
    /**
     * @return List of the comments on a course
     */
    public ArrayList<Comment> getAllComments(){
        return comments;
    }
    /**
     * @return list of reviews for a course
     */
    public ArrayList<Review> getAllReviews(){
        return reviews;
    }
    /**
     * @return The language of a course
     */
    public Language getLanguage(){
        return language;
    }
    /**
     * @return The title of the course
     */
    public String getTitle(){
        return title;
    }
    /***
     * @return the number of reviews
     */
    public int numReviews() {
        return reviews.size();
    }
    /**
     * @return the number of comments
     */
    public int numComments() {
        return comments.size();
    } 
    /**
     * @return the number of modules
     */
    public int numModules() {
        return modules.size();
    }
    /**
     * @return Review at a given index
     * @param int index = The index  of the list to grab the review from
     */
    public Review getReview(int index) {      
        return reviews.get(index);
    }
    /**
     * @return The comment at a given index
     * @param int index = The index of the list to grab the comment from
     */
    public Comment getComment(int index) {
        return this.comments.get(index);
    }
    /**
     * @return The module at the specified index of the module list
     * @param int index = The index in the list at which the module is to be grabbed 
     */
    public Module getModule(int index) { 
        return this.modules.get(index);
    }
    /**
     * Adds a review to the list of the reviews
     * @param Review review = The review to be added
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }
    /**
     * Adds a comment to the list of comments
     * @param Comment comment = The comment to be added
     */
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
    /**
     * @return The unique ID of the course
     */
    public UUID getCourseID() {
        return this.courseID;
    }
    /**
     * @return The average score of the course across all reviews
     */
    public float getAvgRating(){
        float ret = 0.0f;

        for (int i = 0; i < reviews.size(); i++){
            ret += reviews.get(i).getRating();
        }

        return ret/reviews.size();
    }
}

 


