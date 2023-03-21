package source.Menus;
import source.LMSFacade;
import source.User;
import source.EnrolledCourse;
import java.util.ArrayList;

public class EnrolledCoursesMenu extends Menu{

    private ArrayList<EnrolledCourse> enrolledCourses;
    private int courseIndex;
    public EnrolledCoursesMenu(LMSFacade facade, Menu prevMenu, ArrayList<EnrolledCourse> courses) {
        this.facade = facade;
        this.courseIndex = 0;
        header = courses.get(courseIndex).toString();
        options = new String[]{"Next Course", "Previous Course", "View Comments", "Back"};
    }

    public void select(int selection) {

    }

}
