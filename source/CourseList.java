package source;

import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;

public class CourseList {
    
    private HashMap<UUID, Course> courses;
    private static CourseList courseList;

    private CourseList(ArrayList<Course> courses) {
        this.courses = new HashMap<>();
        for (int c = 0; c < courses.size(); c++) {
            addCourse(courses.get(c));
        }
    
    }

    public static CourseList getInstance(ArrayList<Course> courses) {
        if (courseList != null) {
            return courseList;
        }
        return new CourseList(courses);
    }

    public Course getCourse(UUID courseID) {
        return courses.get(courseID);
    }

    /*public Course getCourse(int index) {
    
    }*/

    public void addCourse(Course course) {
        courses.put(course.getCourseID(), course);
    }

    public void updateCourse(Course course) {
        courses.remove(course.getCourseID());
        courses.put(course.getCourseID(), course);
    }

    public int numCourses() {
        return courses.size();
    }

    /*public String toString() {
        return "";
    }*/

    public User getCurrentUser() {
        return new User(null, null, null, null, null, null, null, null, null, null);
    }

    public HashMap<UUID, Course> getAllCourses() {
        return courses;
    }
}
