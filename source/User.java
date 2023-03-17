package source;
import java.util.ArrayList;
//import java.util.Date;
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

    public String getPassword(){
        return password;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
    
    public String getEmail(){
        return email;
    }

    public Date getDOB(){
        return dob;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getUsername() {
        return username;
    }

    public void addCC(Course course) {
        createdCourses.add(course.getCourseID());
    }

    public UUID getCC(int index) {
        return createdCourses.get(index);
    }

    public ArrayList<UUID> getAllCC() {
        return createdCourses;
    }

    public int numCC() {
        return createdCourses.size();
    }

    public void addEC(EnrolledCourse course) {
        enrolledCourses.add(course);
    }

    public EnrolledCourse getEC(int index) {
        return enrolledCourses.get(index);
    }

    public ArrayList<EnrolledCourse> getAllEC() {
        return enrolledCourses;
    }

    public int numEC() {
        return enrolledCourses.size();
    }


    // Temporary toString for testing data loading
    public String dbgToString(){
        String ret = 
        "ID: "+userID+"\nAccountType: "+accountType+"\nFirst: "
        +firstName+"\nLast: "+lastName+"\nUsername: "+username
        +"\nEmail: "+email+"\nPassword: "+password+"\nDOB: "+dob+"\nCC: ";

        for (int i = 0; i < createdCourses.size(); i++){
            ret += "\n\t"+createdCourses.get(i);
        }
        ret += "\nEC: ";

        for (int i = 0; i < enrolledCourses.size(); i++){
            ret += "\n\t"+enrolledCourses.get(i);
        }
        ret += "\n";

        return ret;
    }
}
   