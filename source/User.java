package source;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class User {
    private UUID userID;
    private AccountType accountType;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Date dob;
    private ArrayList<UUID> createdCourses;
    private ArrayList<EnrolledCourse> enrolledCourses;
    
    public User(UUID userID, AccountType accountType, 
                String firstName, String lastName, 
                String username, String email, String password,
                Date dob, ArrayList<UUID> createdCourses, 
                ArrayList<EnrolledCourse> enrolledCourses){
        this.userID = userID;
        this.accountType = accountType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.createdCourses = createdCourses;
        this.enrolledCourses = enrolledCourses;
    }

    public UUID getID(){
        return userID;
    }
}
   