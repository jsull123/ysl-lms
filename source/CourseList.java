package source;

import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;

public class CourseList {
    private HashMap<UUID, Course> courses;
    private static CourseList courseList;

    /**
     * Constructor for a CourseList object
     * 
     * @param ArrayList<Course> courses = List of courses
     */
    private CourseList(ArrayList<Course> courses) {
        this.courses = new HashMap<>();

        if (courses == null)
            return;

        add(courses);
    }

    /**
     * @return Singleton instance for the CourseList constructor
     * @param ArrayList<COurse> courses = List of courses
     */
    public static CourseList getInstance(ArrayList<Course> courses) {
        if (courseList != null) {
            return courseList;
        }
        courseList = new CourseList(courses);
        return courseList;
    }

    /**
     * @return Course at a specified ID
     * @param UUID courseID = ID to find course at
     */
    public Course getCourse(UUID courseID) {
        return courses.get(courseID);
    }

    /**
     * Adds a course to the course list
     * 
     * @param Course course = course to be added
     */
    public void addCourse(Course course) {
        courses.put(course.getCourseID(), course);
    }

    /**
     * Updates the information in a course
     * 
     * @param Course course = The course to be modified
     */
    public void updateCourse(Course course) {
        courses.remove(course.getCourseID());
        courses.put(course.getCourseID(), course);
        CourseDataProcessor.saveData(this);
    }

    /**
     * @return The number of courses in the list
     */
    public int numCourses() {
        return courses.size();
    }

    public void add(ArrayList<Course> courses){
        for (int c = 0; c < courses.size(); c++) {
            addCourse(courses.get(c));
        }
    }

    public void clear(){
        courses.clear();
    }

    /**
     * @return All the course of a given langauge
     * @param String Language = The language to find course by
     */
    public ArrayList<Course> getAllByLanguage(String language) {
        Course[] c = new Course[courses.size()];
        courses.values().toArray(c);

        ArrayList<Course> ret = new ArrayList<>();
        for (int i = 0; i < c.length; i++) {
            if (c[i].getLanguage().toString().toLowerCase().equals(language.toLowerCase())) {
                ret.add(c[i]);
            }
        }

        return ret;
    }

    /**
     * Constructor to turn courses from ArrayList to array
     */
    public Course[] toArray() {
        Course[] c = new Course[courses.size()];
        courses.values().toArray(c);
        return c;
    }
}
