package source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class UserList {
    private HashMap<UUID, User> users;
    private HashMap<UUID, String> passwordMap;
    private User currentUser;
    private static UserList userList; 
    /**
     * Singleton constructor for a user list
     * @param ArrayList<User> users = the list of users
     */
    private UserList(ArrayList<User> users){
        this.users = new HashMap<>();
        this.passwordMap = new HashMap<>();
        for(int i = 0; i < users.size(); i++) {
            addUser(users.get(i));
        }
        currentUser = null;
    }
    /**
     * @return An instance of UserList
     * @param ArrayList<User> users = The list of users
     */
    public static UserList getInstance(ArrayList<User> users){
        if (userList != null) {
            return userList;
        }
        userList = new UserList(users);
        return userList;
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }
    /**
     * @return The user from his username
     */
    public User getUser(String username){
        for(User user : this.users.values()){
            if(username.equals(user.getUsername())){
                return user;
            }
        }
        return null;
    }
    /**
     * @return an array form of the user list
     */
    public User[] toArray(){
        User[] u = new User[users.size()];
        users.values().toArray(u);
        return u;
    }
    /**
     * @return The user from his ID
     */
    public User getUser(UUID userID){
        return users.get(userID);
    }

    // probably not necessary
    public int numUsers(){
        return users.size();
    }
    /**
     * Adds a user to the password map
     * @param User user = The user to be added
     */
    public void addUser(User user){
        users.put(user.getID(), user);
        passwordMap.put(user.getID(), user.getPassword());
    }
    /**
     * @return The current user
     */
    public User getCurrentUser(){
        return currentUser;
    }

    public void clear(){
        users.clear();
        passwordMap.clear();
        currentUser = null;
    }

    public void add(ArrayList<User> users){
        for (int i = 0; i < users.size(); i++) {
            addUser(users.get(i));
        }
    }
}
