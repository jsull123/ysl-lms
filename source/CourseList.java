package source;

import java.util.HashMap;
import java.util.UUID;

public class CourseList {
    
    private HashMap<UUID, Course> courses;
    private static CourseList courseList;

    private CourseList(HashMap<UUID, Course> courses) {
            courseList = this;
    }

    public static CourseList getInstance() {
        if (courseList != null) {
            return courseList;
        }
        return new CourseList(new HashMap<UUID, Course>());
    }

    public Course getCourse(UUID courseID) {
        return courses.get(courseID);
    }

<<<<<<< HEAD
    public Course getCourse(int index) {
        return new Course(null, null, null, 0, null, null, null, null, null);
    }
=======
    /*public Course getCourse(int index) {
    
    }*/
>>>>>>> 19b760e2d395997b800c2379c27d52b848175ea6

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
