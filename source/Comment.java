import java.util.UUID;
import java.util.Date;
import java.util.ArrayList;

private UUID authorID;
private String comment;
private Date dateAdded;
private ArrayList<Comment> comments;

public Comment(UUID authorID, float rating, String review, Date dateAdded){
    this.dateAdded = dateAdded;
    this.UUID = authorID;
    this.comment = review;
    comments.add(review);
}


public int numComments(){
    return 1;
}

public Comment getComment(int index) {
    
    Comment CommentOfInterest = comments.get(0);
    return CommentOFInterest;
}

public void toString(){
    System.out.pritnln("Stub");
}