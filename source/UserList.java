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
        for(int i = 0; i < users.size; i++) {
            User newUser = users.get(i);
            this.users.put(newUser.getID, newUser);
        }
        currentUser = null;
    }

    public static UserList getInstance(){
        UserList userList = new UserList()
        return null;
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public User getUser(String username){
        for(User user: this.users.values()){
            if(username.equals(user.getUsername)){
                return user;
            } else {
                return null;
            }
        }
    }

    public User getUser(UUID userID){
        return users.get(userID);
    }

//    public User getUsers(int index){
//        
//    }

    public int numUsers(){
        return users.size();
    }

    public void addUser(User user){
        users.put(user.getID(), user);
    }
}
