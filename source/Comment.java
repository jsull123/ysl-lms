package source;

import java.util.UUID;
import java.util.Date;
import java.util.ArrayList;

public class Comment{
    private UUID authorID;
    private String comment;
    private Date dateAdded;
    private ArrayList<Comment> comments;
    
    public Comment(UUID authorID, String comment, Date dateAdded, ArrayList<Comment> comments){
        this.dateAdded = dateAdded;
        this.authorID = authorID;
        this.comment = comment;
        this.comments = comments;
    }
    
    
    public int numComments(){
        return 1;
    }
    
    public Comment getComment(int index) {
        
        Comment CommentOfInterest = comments.get(0);
        return CommentOfInterest;
    }
    
    public String toString(){
        return "";
    }
}
