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

    public Course[] toArray(){
        Course[] c = new Course[courses.size()];
        courses.values().toArray(c);
        return c;
    }
}
