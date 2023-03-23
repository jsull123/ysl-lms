package source.Menus;
import source.LMSFacade;
import source.UserList;
import source.CourseList;
import source.EnrolledCourse;
import java.util.ArrayList;

public class EnrolledCoursesMenu extends Menu{

    private ArrayList<EnrolledCourse> enrolledCourses;
    private int courseIndex;
    private int numCourses;

    public EnrolledCoursesMenu(LMSFacade facade, Menu pMenu, ArrayList<EnrolledCourse> courses) {
        if (courses.size() == 0){
            facade.setCurrentMenu(pMenu);
            facade.getCurrentMenu().getSelection("You are not enrolled in any courses");
            return;
        }

        header = courses.get(courseIndex).toString(); 
        this.facade = facade;
        this.courseIndex = 0;
        this.pMenu = pMenu;
        numCourses = courses.size();
        options = new String[]{"Next Course", "Previous Course", "View Comments", "View Modules", "Back"};
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
                if (courseIndex + 1 >= numCourses) {
                    courseIndex = 0;
                }
                else courseIndex++;
                header = enrolledCourses.get(courseIndex).toString();
                getSelection();
                break;
            case 2:
                if (courseIndex <= 0) {
                    courseIndex = numCourses - 1;
                }
                else courseIndex--;
                header = enrolledCourses.get(courseIndex).toString();
                getSelection();
                break;
            case 3:
                facade.setCurrentMenu(new ViewComments(facade, this,
                  CourseList.getInstance(null).getCourse(enrolledCourses.get(courseIndex).getID()).getAllComments()));
                  UserList.getInstance(null);
                break;
            case 4:
                facade.setCurrentMenu(new ViewModules(facade, pMenu));
            case 5:
                facade.setCurrentMenu(pMenu).getSelection();
                break;
        }
    }

}
