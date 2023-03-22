package source;

import java.util.UUID;
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

    public void addReply(Comment reply){
        replies.add(reply);
    }

    public ArrayList<Comment> getReplies(){
        return replies;
    }

    public String toString(){
        String plurality = "replies";
        if (replies.size() == 1) plurality = "reply";

        return UserList.getInstance(null).getUser(authorID).getUsername()+
        ": "+comment+
        "\n("+replies.size()+" "+plurality+")\n";
    }
}