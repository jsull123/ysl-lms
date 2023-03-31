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
    /**
     * Constructor for a User object
     * @param UUID userID = The unique ID for a user
     * @param AccountType accountType = The type of account the new user wants to make
     * @param String firstName = User's first name
     * @param String lastName = User's last name
     * @param String username = The user's chosen username
     * @param String email = The user's email
     * @param String password = The user's chosen password
     * @param Date dob = The user's date of birth
     * @param ArrayList<UUID> createdCourses = The courses created by a particular person
     * @param ArrayList<EnrolledCourse> enrolledCourses = The course enrolled in by a particular user 
     */
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
    /**
     * @return The user's ID
     */
    public UUID getID(){
        return userID;
    }
    /**
     * @return The user's password
     */
    public String getPassword(){
        return password;
    }
    /**
     * @return The user's first name
     */
    public String getFirstName(){
        return firstName;
    }
    /**
     * @return The user's last name
     */
    public String getLastName(){
        return lastName;
    }
    /**
     * @return The user's email
     */
    public String getEmail(){
        return email;
    }
    /**
     * @return The user's date of birth
     */
    public Date getDOB(){
        return dob;
    }
    /**
     * @return The user's account type
     */
    public AccountType getAccountType() {
        return accountType;
    }
    /**
     * @return The user's username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Adds a course that an author created to his list of created courses
     */
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
   