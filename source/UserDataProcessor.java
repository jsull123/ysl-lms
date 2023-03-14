package source;

import java.io.FileReader;

public class UserDataProcessor {
    public final String filename = "json/courses.json";

    public static void loadData(UserList userList){
        try{
            
            FileReader reader = new FileReader(filename);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void saveData(UserList userList){

    }
}
