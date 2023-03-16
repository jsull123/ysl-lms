package source;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;
import javax.xml.crypto.Data;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UserDataProcessor {

    public static ArrayList<EnrolledCourse> loadEnrolledCourses(JSONObject jUser){
        ArrayList<EnrolledCourse> enrolledCourses = new ArrayList<>();
        JSONArray jEnrolledCourses = (JSONArray)jUser.get(DataConstants.ENROLLED_COURSES);
        
        for (int i = 0; i < jEnrolledCourses.size(); i++){
            ArrayList<ArrayList<Boolean>> moduleProgress = new ArrayList<>();
            
            JSONObject jEnrolledCourse = (JSONObject)jEnrolledCourses.get(i);
            JSONArray jModProgress = (JSONArray)jEnrolledCourse.get(DataConstants.MODULE_PROGRESS);

            for (int j = 0; j < jModProgress.size(); j++){
                ArrayList<Boolean> progress = new ArrayList<>();
                JSONArray jProgress = (JSONArray)((JSONObject)jModProgress.get(j)).get(DataConstants.PROGRESS);
                
                for (int p = 0; p < jProgress.size(); p++){
                    progress.add((Boolean)((JSONObject)(jProgress.get(p))).get(DataConstants.IS_COMPLETE));
                    System.out.println(progress.get(progress.size()-1));
                }
                moduleProgress.add(progress);
            }

            enrolledCourses.add(new EnrolledCourse(UUID.fromString((String)jEnrolledCourse.get(DataConstants.COURSE_ID)), moduleProgress));
        }


        return enrolledCourses;
    }

    public static ArrayList<User> loadData(){
        ArrayList<User> users = new ArrayList<>();
        try{
            FileReader reader = new FileReader(DataConstants.USERS_FILE_NAME);
            JSONArray jUsers = (JSONArray)new JSONParser().parse(reader);
            
            for (int i = 0; i < jUsers.size(); i++){
                JSONObject jUser = (JSONObject)jUsers.get(i);
                ArrayList<EnrolledCourse> enrolledCourses = loadEnrolledCourses(jUser);
                ArrayList<UUID> createdCourses = new ArrayList<>();

                JSONArray jCreatedCourses = (JSONArray)jUser.get(DataConstants.CREATED_COURSES);
                for (int c = 0; c < jCreatedCourses.size(); c++){
                    createdCourses.add(UUID.fromString((String)((JSONObject)jCreatedCourses.get(c)).get(DataConstants.COURSE_ID)));
                }

                users.add(new User(
                    UUID.fromString((String)jUser.get(DataConstants.USER_ID)),
                    AccountType.fromString((String)jUser.get(DataConstants.ACCOUNT_TYPE)),
                    

                ));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return users;
    }

    public static void saveData(UserList userList){

    }
}
