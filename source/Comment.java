package source;

import java.util.UUID;
import java.util.ArrayList;

public class Comment {
    private UUID authorID;
    private String comment;
    private Date dateAdded;
    private ArrayList<Comment> replies;
    /**
     * Creates a Comment object
     * @param UUID authorID = The unique ID for the author of a comment
     * @param String comment = The actual comment written
     * @param Date dateAdded = Date the comment was added
     */
    public Comment(UUID authorID, String comment, Date dateAdded, ArrayList<Comment> replies){
        this.authorID = authorID;
        this.comment = comment;
        this.dateAdded = dateAdded;
        this.replies = replies;
    }
    /**
     * Gets the author's ID
     * @return The author's unique ID
     */
    public UUID getAuthorID(){
        return authorID;
    }
    /**
     * Gets the author's comment
     * @return The author's comment
     */
    public String getComment(){
        return comment;
    }
    /**
     * Gets the date the author wrote the comment
     * @return The date the auhtor wrote the comment
     */
    public Date getDateAdded(){
        return dateAdded;
    }
    /**
     * Makes a reply to a comment
     * @param Comment reply = The actual reply to the comment
     */
    public void addReply(Comment reply){
        replies.add(reply);
    }
    /**
     * @return The list of replies to a comment
     */
    public ArrayList<Comment> getReplies(){
        return replies;
    }
    /**
     * @return The comment and replies to it
     */
    public String toString(){
        String plurality = "replies";
        if (replies.size() == 1) plurality = "reply";
        User user = UserList.getInstance(null).getUser(authorID);

        return user.getFirstName()+" "+user.getLastName()+
        ": "+comment+
        "\n("+replies.size()+" "+plurality+")\n";
    }
}