package source;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CourseDataProcessor {
    
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
