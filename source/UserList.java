package source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class UserList {
    private HashMap<UUID, User> users;
    private HashMap<UUID, String> passwordMap;
    private User currentUser;
    private static UserList userList; 

    private UserList(ArrayList<User> users){
        this.users = new HashMap<>();
        this.passwordMap = new HashMap<>();
        for(int i = 0; i < users.size(); i++) {
            addUser(users.get(i));
        }
        currentUser = null;
    }

    public static UserList getInstance(ArrayList<User> users){
        return new UserList(users);
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public User getUser(String username){
        for(User user : this.users.values()){
            if(username.equals(user.getUsername())){
                return user;
            }
        }
        return null;
    }

    public User[] toArray(){
        User[] u = new User[users.size()];
        users.values().toArray(u);
        return u;
    }

    public User getUser(UUID userID){
        return users.get(userID);
    }

    // probably not necessary
    public int numUsers(){
        return users.size();
    }

    public void addUser(User user){
        users.put(user.getID(), user);
        passwordMap.put(user.getID(), user.getPassword());
    }
    
    public User getCurrentUser(){
        return currentUser;
    }

     // Temporary toString for testing data loading
    public String dbgToString(){
        User[] u = new User[users.size()];
        users.values().toArray(u);

        String ret = "";
        for (int i = 0; i < users.size(); i++){
            ret += "User "+(i+1)+"\n"+u[i].dbgToString()+"\n";
        }

        return ret;
    }
}
