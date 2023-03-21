package source;

import java.util.UUID;
import java.util.Date;
import java.util.ArrayList;

public class Comment {
    private UUID authorID;
    private String comment;
    private Date dateAdded;
    private ArrayList<Comment> replies;

    public Comment(UUID authorID, String comment, Date dateAdded, ArrayList<Comment> replies){
        this.authorID = authorID;
        this.comment = comment;
        this.dateAdded = dateAdded;
        this.replies = replies;
    }

    public UUID getAuthorID(){
        return authorID;
    }

    public String getComment(){
        return comment;
    }

    public Date getDateAdded(){
        return dateAdded;
    }

    public ArrayList<Comment> getReplies(){
        return replies;
    }
}