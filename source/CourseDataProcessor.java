package source;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
    
    private static ArrayList<Review> loadReviews(JSONObject course) {
        ArrayList<Review> reviews = new ArrayList<>();
        try{         

                JSONArray jReviews = (JSONArray)course.get(DataConstants.REVIEWS);
                for (int r = 0; r < jReviews.size(); r++){
                    JSONObject reviewObject = (JSONObject)jReviews.get(r);
                    Review review = new Review(UUID.fromString((String)course.get(DataConstants.AUTHOR_ID)), 
                    (float)reviewObject.get(DataConstants.RATING), 
                    (String)reviewObject.get(DataConstants.REVIEW), 
                    Date.fromString((String)reviewObject.get(DataConstants.DATE_ADDED)));
                    reviews.add(review);
                }     

            
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return reviews;
    }

    private static ArrayList<Module> loadModules(JSONObject course) {
        ArrayList<Module> modules = new ArrayList<>();
        try{         
                JSONArray jModules = (JSONArray)course.get(DataConstants.MODULES);
                for (int m = 0; m < jModules.size(); m++){
                    JSONObject moduleObject = (JSONObject)jModules.get(m);
                    Module module = new Module((String)course.get(DataConstants.TITLE),
                    (String)course.get(DataConstants.TOPIC), 
                    loadContent((JSONArray)moduleObject.get(DataConstants.CONTENT)));
                    modules.add(module);
                }     

            
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return modules;
    }

   //  UNFINISHED: Will finish once lesson is made
   private static ArrayList<Content> loadContent(JSONArray contentArray) {
        ArrayList<Content> content = new ArrayList<>();
        for (int c = 0; c < contentArray.size(); c++){
            JSONObject contentObject = (JSONObject)contentArray.get(c);
            
        }     

        return content;
    }

    public static CourseList loadData() {
        ArrayList<Course> courses = new ArrayList<>();
        try{
            FileReader reader = new FileReader(DataConstants.COURSES_FILE_NAME);
            JSONArray jCourses = (JSONArray)new JSONParser().parse(reader);

            for (int c = 0; c < jCourses.size(); c++) {
                JSONObject jCourse = (JSONObject)jCourses.get(c);
                ArrayList<Review> reviews = loadReviews(jCourse);
                ArrayList<Module> modules = loadModules(jCourse);
                ArrayList<Comment> comments = loadComments((JSONArray)jCourse.get(DataConstants.COMMENTS));
                courses.add(new Course(
                    UUID.fromString(((String)jCourse.get(DataConstants.COURSE_ID))), 
                    (String)jCourse.get(DataConstants.TITLE), 
                    (String)jCourse.get(DataConstants.LANGUAGE), 
                    (Float)jCourse.get(DataConstants.RATING), 
                    (String)jCourse.get(DataConstants.DESCRIPTION),  
                    UUID.fromString(((String)jCourse.get(DataConstants.AUTHOR_ID))), 
                    reviews,
                    comments,
                    modules)); 
            }

            
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return CourseList.getInstance(courses);
    }

    public static void saveData(CourseList courseList){

    }

}
