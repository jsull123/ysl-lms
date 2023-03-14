package source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class UserList {
    private HashMap<UUID, User> users;
    private HashMap<String, String> passwordMap;
    private User currentUser;
    private static UserList userList; 

    private UserList(ArrayList<User> users){
        // make hash maps
        currentUser = null;
    }

    public static UserList getInstance(){
        return null;
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public User getUser(String username){
        return null;
    }

    public User getUser(UUID userID){
        return users.get(userID);
    }

    public User getUsers(int index){
        return null;
    }

    public int numUsers(){
        return users.size();
    }

    public void addUser(User user){
        users.put(user.getID(), user);
    }
}
