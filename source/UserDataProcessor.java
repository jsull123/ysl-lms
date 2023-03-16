package source;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UserDataProcessor {
    public final static String filename = "json/courses.json";

    public static void loadData(UserList userList){
        try{
            /*
             *   FileReader reader = new FileReader("json/courses.json");
            JSONArray courses = (JSONArray)new JSONParser().parse(reader);
            
            if (courses.size() >= 1){
                JSONObject jcourse = (JSONObject)courses.get(0);
                JSONArray jcomments = (JSONArray)jcourse.get("comments");
                comments = loadComments(jcomments);
            }

             */
            FileReader reader = new FileReader(filename);
            JSONArray j_users = (JSONArray)new JSONParser().parse(reader);
            
            for (int i = 0; i < j_users.size(); i++){
                JSONObject j_user = (JSONObject)j_users.get(i);
                JSONArray j_enrolledCourses = j_users.
                ArrayList<EnrolledCourse> enrolledCourses = new ArrayList<>();
                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void saveData(UserList userList){

    }
}
