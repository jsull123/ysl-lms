package source;

import java.io.FileReader;
import java.io.FileWriter;
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
    
    public static CourseList loadData(){
        ArrayList<User> users = new ArrayList<>();
        try{
            FileReader reader = new FileReader(DataConstants.COURSES_FILE_NAME);
            JSONArray jCourses = (JSONArray)new JSONParser().parse(reader);
            
            for (int i = 0; i < jCourses.size(); i++){
                JSONObject jCourse = (JSONObject)jCourses.get(i);
                ArrayList<Review> reviews = new ArrayList<>();
                ArrayList<Module> modules = new ArrayList<>();

                JSONArray jReviews = (JSONArray)jCourse.get(DataConstants.REVIEWS);
                for (int r = 0; r < jReviews.size(); r++){
                    JSONObject reviewObject = (JSONObject)jReviews.get(r);
                    Review review = new Review(UUID.fromString((String)jCourse.get(DataConstants.AUTHOR_ID)), (float)reviewObject.get(DataConstants.RATING), 
                    (String)reviewObject.get(DataConstants.REVIEW), 
                    Date.fromString((String)reviewObject.get(DataConstants.DATE_ADDED)));
                    reviews.add(review);
                }   

                JSONArray jModules = (JSONArray)jCourse.get(DataConstants.MODULES);
                for (int m = 0; m < jModules.size(); m++){
                    JSONObject moduleObject = (JSONObject)jModules.get(m);
                    Review module = 
                }   

                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return;
    }

    public static void saveData(CourseList courseList){

    }

}
