package source;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UserDataProcessor {
    public final static String filename = "json/courses.json";

    public static void loadData(UserList userList){
        try{
            
            FileReader reader = new FileReader(filename);
            JSONArray jUsers = (JSONArray)new JSONParser().parse(reader);

            // Loop users
            for (int i = 0; i < jUsers.size(); i++){
                JSONObject jUser = (JSONObject)jUsers.get(i);

                JSONArray jCourses = (JSONArray)jUser.get(jUser);
                ArrayList<EnrolledCourse> enrolledCourses = new ArrayList<>();
                
                // Loop enrolled courses
                for (int j = 0; j < jCourses.size(); j++){
                    JSONObject jCourse = (JSONObject)jCourses.get(i);

                    ArrayList<ArrayList<Integer>> moduleProgress = new ArrayList<>();
                    JSONArray jmoduleProgress = (JSONArray)jCourse.get(DataConstants.MODULE_PROGRESS);
                    for (int p = 0; p < jmoduleProgress.size(); p++){
                        moduleProgress.add(new ArrayList<>());
                        JSONArray jprogress = (JSONArray)jmoduleProgress.get(p);
                        for (int q = 0; q < jprogress.size(); q++){
                            
                        }
                    }

                    //enrolledCourses.add(new EnrolledCourse(UUID.fromString((String)jCourse.get(DataConstants.COURSE_ID))));
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void saveData(UserList userList){

    }
}
