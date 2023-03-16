package source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CourseList {
    
    private HashMap<UUID, Course> courses;
    private static CourseList courseList;

    private CourseList(ArrayList<Course> courses) {
            courseList = this;
    }

    public static CourseList getInstance() {
        if (courseList != null) {
            return courseList;
        }
        return new CourseList(new ArrayList<>());
    }

    public Course getCourse(UUID courseID) {
        return new Course(courseID, null, null, 0, null, courseID, null, null, null);
    }

    public Course getCourse(int index) {
        return new Course(null, null, null, 0, null, null, null, null, null);
    }

    public void addCourse(Course course) {

    }

    public void updateCourse(Course course) {

    }

    public int numCourses() {
        return 0;
    }

    public String toString() {
        return "";
    }

    public User getCurrentUser() {
        return new User(null, null, null, null, null, null, null, null, null, null);
    }

    public ArrayList<Course> getAllCourses() {
        return new ArrayList<>();
    }
}
