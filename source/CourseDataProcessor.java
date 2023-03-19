package source;

import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CourseDataProcessor {

    private static JSONArray commentsToJson(ArrayList<Comment> comments){
        JSONArray ret = new JSONArray();
        
        for (int i = 0; i < comments.size(); i++){
            Comment comment = comments.get(i);
            JSONObject jComment = new JSONObject();
            jComment.put(DataConstants.AUTHOR_ID, comment.getAuthorID().toString());
            jComment.put(DataConstants.COMMENT, comment.getComment());
            jComment.put(DataConstants.DATE_ADDED, comment.GetDateAdded().toString());
            jComment.put(DataConstants.REPLIES, commentsToJson(comment.GetReplies()));

            ret.add(jComment);
        }
        return ret;
    }

    private static ArrayList<Comment> loadComments(JSONArray comments){
        ArrayList<Comment> ret = new ArrayList<>();

        for (int i = 0; i < comments.size(); i++){
            JSONObject jComment = (JSONObject)comments.get(i);
            
            ret.add(new Comment(
                UUID.fromString(((String)jComment.get(DataConstants.AUTHOR_ID))),
                (String)jComment.get(DataConstants.COMMENT),
                Date.fromString((String)jComment.get(DataConstants.DATE_ADDED)),
                loadComments((JSONArray)jComment.get(DataConstants.REPLIES))
            ));
        }
        return ret;
    }
    
    public static void loadData(CourseList courseList){

    }

    public static void saveData(CourseList courseList){

    }

}
