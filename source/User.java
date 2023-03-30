package source;
import java.util.ArrayList;
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

    // Turn the UUID list into a list of courses
    private ArrayList<Course> getCoursesFromUUID() {
        ArrayList<Course> courses = new ArrayList<>();
        for(int i = 0; i < createdCourses.size(); i++) {
            Course currCourse = CourseList.getInstance(null).getCourse(createdCourses.get(i));
            courses.add(currCourse);
        }
        return courses;
    }

    public Course getCC(int index) {
        return getCoursesFromUUID().get(index);
    }

    public ArrayList<UUID> getAllCreatedCourses() {
        return createdCourses;
    }

    public ArrayList<UUID> getCreatedCoursesIDs(){
        return createdCourses;
    }

    public int numCC() {
        return createdCourses.size();
    }

    public void addEC(EnrolledCourse course) {
        for (int i = 0; i < enrolledCourses.size(); i++){
            if (enrolledCourses.get(i).getID() == course.getID())
                return;
        }
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
}
   