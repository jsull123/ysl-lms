package source;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UserDataProcessor {

    private static ArrayList<ModuleProgress> loadModuleProgress(JSONArray jModuleProgress){
        ArrayList<ModuleProgress> moduleProgress = new ArrayList<>();
        try{
            for (int i = 0; i < jModuleProgress.size(); i++){
                JSONObject jObject = (JSONObject)jModuleProgress.get(i);

                moduleProgress.add(new ModuleProgress(
                    Float.valueOf((String)jObject.get(DataConstants.QUIZ_GRADE)),
                    Boolean.valueOf((String)jObject.get(DataConstants.HAS_PASSED))
                ));
            }
        }catch(Exception e){};
        return moduleProgress;
    }
    
    private static ArrayList<EnrolledCourse> loadEnrolledCourses(JSONArray jEnrolledCourses){
        ArrayList<EnrolledCourse> enrolledCourses = new ArrayList<>();
        try{
            for (int i = 0; i < jEnrolledCourses.size(); i++){
                JSONObject jEnrolledCourse = (JSONObject)jEnrolledCourses.get(i);

                enrolledCourses.add(new EnrolledCourse(
                    UUID.fromString((String)jEnrolledCourse.get(DataConstants.COURSE_ID)),
                    loadModuleProgress((JSONArray)jEnrolledCourse.get(DataConstants.MODULE_PROGRESS))
                ));
            }
        }catch(Exception e){};
        return enrolledCourses;
    }

      // Create a JSONArray out of array list of UUID
    private static JSONArray saveCreatedCourses(ArrayList<UUID> createdCourses){
        JSONArray jCreatedCourses = new JSONArray();
    
        for (int i = 0; i < createdCourses.size(); i++){
            JSONObject jCourseId = new JSONObject();
            jCourseId.put(DataConstants.COURSE_ID, createdCourses.get(i).toString());
            jCreatedCourses.add(jCourseId);
        }
        return jCreatedCourses;
    }

    private static JSONArray saveModuleProgress(ArrayList<ModuleProgress> moduleProgress){
        JSONArray jModuleProgress = new JSONArray();
    
        for (int i = 0; i < moduleProgress.size(); i++){
            JSONObject jObject = new JSONObject();
            jObject.put(DataConstants.QUIZ_GRADE, Float.valueOf(moduleProgress.get(i).getQuizGrade()).toString());
            jObject.put(DataConstants.HAS_PASSED, Boolean.valueOf(moduleProgress.get(i).getHasPassed()).toString());
            jModuleProgress.add(jObject);
        }
        return jModuleProgress;
    }

    // Create a JSONArray out of array list of EnrolledCourse
    private static JSONArray saveEnrolledCourses(ArrayList<EnrolledCourse> enrolledCourses){
        JSONArray jEnrolledCourses = new JSONArray();

        for (int i = 0; i < enrolledCourses.size(); i++){
            JSONObject jEnrolledCourse = new JSONObject();
            jEnrolledCourse.put(DataConstants.COURSE_ID, enrolledCourses.get(i).getID().toString());
            jEnrolledCourse.put(DataConstants.MODULE_PROGRESS, saveModuleProgress(enrolledCourses.get(i).getModuleProgress()));
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
                ArrayList<EnrolledCourse> enrolledCourses = loadEnrolledCourses((JSONArray)jUser.get(DataConstants.ENROLLED_COURSES));
                ArrayList<UUID> createdCourses = new ArrayList<>();

                JSONArray jCreatedCourses = (JSONArray)jUser.get(DataConstants.CREATED_COURSES);
                for (int c = 0; c < jCreatedCourses.size(); c++){
                    createdCourses.add(UUID.fromString((String)((JSONObject)jCreatedCourses.get(c)).get(DataConstants.COURSE_ID)));
                }   

                users.add(new User(
                    UUID.fromString((String)jUser.get(DataConstants.USER_ID)),
                    AccountType.fromString((String)jUser.get(DataConstants.ACCOUNT_TYPE)),
                    (String)jUser.get(DataConstants.FIRST_NAME),
                    (String)jUser.get(DataConstants.LAST_NAME),
                    (String)jUser.get(DataConstants.USERNAME),
                    (String)jUser.get(DataConstants.EMAIL),
                    (String)jUser.get(DataConstants.PASSWORD),
                    Date.fromString((String)jUser.get(DataConstants.DOB)),
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
            jUser.put(DataConstants.ENROLLED_COURSES, saveEnrolledCourses(users[i].getAllEC()));
            jUser.put(DataConstants.CREATED_COURSES, saveCreatedCourses(users[i].getCreatedCoursesIDs()));

            // User to JSONArray of users
            jUsers.add(jUser);
        }
    
        // Write JSONArray to file
        try{
            FileWriter file = new FileWriter(DataConstants.USERS_FILE_NAME);
            file.write(jUsers.toJSONString());
            file.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
