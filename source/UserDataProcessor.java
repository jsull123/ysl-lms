package source;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UserDataProcessor {

    // Load enrolled courses from a user into and array list
    private static ArrayList<EnrolledCourse> loadEnrolledCourses(JSONObject jUser){
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
                }
                moduleProgress.add(progress);
            }

            enrolledCourses.add(new EnrolledCourse(UUID.fromString((String)jEnrolledCourse.get(DataConstants.COURSE_ID)), moduleProgress));
        }


        return enrolledCourses;
    }
      // Create a JSONArray out of array list of UUID
    private static JSONArray ccToJson(ArrayList<UUID> createdCourses){
        JSONArray jCreatedCourses = new JSONArray();
    
        for (int i = 0; i < createdCourses.size(); i++){
            JSONObject jCourseId = new JSONObject();
            jCourseId.put(DataConstants.COURSE_ID, createdCourses.get(i).toString());
            jCreatedCourses.add(jCourseId);
        }
        return jCreatedCourses;
    }

    // Create a JSONArray out of array list of EnrolledCourse
    private static JSONArray ecToJson(ArrayList<EnrolledCourse> enrolledCourses){
        JSONArray jEnrolledCourses = new JSONArray();

        for (int i = 0; i < enrolledCourses.size(); i++){
            JSONObject jEnrolledCourse = new JSONObject();
            jEnrolledCourse.put(DataConstants.COURSE_ID, enrolledCourses.get(i).getID().toString());

            JSONArray jModProgress = new JSONArray();
            ArrayList<ArrayList<Boolean>> moduleProgress = enrolledCourses.get(i).getModuleProgress();

            for (int j = 0; j < moduleProgress.size(); j++){
                JSONArray jProgress = new JSONArray();
                for (int l = 0; l < moduleProgress.get(j).size(); l++){
                    JSONObject isComplete = new JSONObject();
                    isComplete.put(DataConstants.IS_COMPLETE, moduleProgress.get(j).get(l));
                    jProgress.add(isComplete);
                }
                jModProgress.add(jProgress);
            }
            jEnrolledCourse.put(DataConstants.MODULE_PROGRESS, jModProgress);
            jEnrolledCourses.add(jEnrolledCourse);
        }
        return jEnrolledCourses;
    }

    // Load all users from users.json into UserList instance
    public static UserList loadData(){
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

                SimpleDateFormat format = new SimpleDateFormat("MM/DD/YYYY");
                users.add(new User(
                    UUID.fromString((String)jUser.get(DataConstants.USER_ID)),
                    AccountType.fromString((String)jUser.get(DataConstants.ACCOUNT_TYPE)),
                    (String)jUser.get(DataConstants.FIRST_NAME),
                    (String)jUser.get(DataConstants.LAST_NAME),
                    (String)jUser.get(DataConstants.USERNAME),
                    (String)jUser.get(DataConstants.EMAIL),
                    (String)jUser.get(DataConstants.PASSWORD),
                    format.parse((String)jUser.get(DataConstants.DOB)),
                    createdCourses,
                    enrolledCourses
                ));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    
        return UserList.getInstance(users);
    }

    public static void saveData(UserList userList){
        JSONArray jUsers = new JSONArray();
        // Get all users
        User[] users = userList.toArray();

        // Create a JSONObject out of each user
        for (int i = 0; i < users.length; i++){ 
    
            JSONObject jUser = new JSONObject();
            jUser.put(DataConstants.USER_ID, users[i].getID().toString()); 
            jUser.put(DataConstants.ACCOUNT_TYPE, users[i].getAccountType().toString()); 
            jUser.put(DataConstants.FIRST_NAME, users[i].getFirstName()); 
            jUser.put(DataConstants.LAST_NAME, users[i].getLastName()); 
            jUser.put(DataConstants.USERNAME, users[i].getUsername()); 
            jUser.put(DataConstants.EMAIL, users[i].getEmail()); 
            jUser.put(DataConstants.PASSWORD, users[i].getPassword()); 
            jUser.put(DataConstants.DOB, users[i].getDOB().toString());
            jUser.put(DataConstants.ENROLLED_COURSES, ecToJson(users[i].getAllEC()));
            jUser.put(DataConstants.CREATED_COURSES, ccToJson(users[i].getAllCC()));

            // User to JSONArray of users
            jUsers.add(jUser);
        }
    
        // Write JSONArray to file
        try{
            // Will be DataConstants.USERS_FILE_NAME
            FileWriter file = new FileWriter("json/testusers.json");
            file.write(jUsers.toJSONString());
            file.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
