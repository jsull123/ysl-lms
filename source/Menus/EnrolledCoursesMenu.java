package source.Menus;
import source.LMSFacade;
import source.User;
import source.EnrolledCourse;
import java.util.ArrayList;

public class EnrolledCoursesMenu extends Menu{

    private ArrayList<EnrolledCourse> enrolledCourses;
    private int courseIndex;
    private int numCourses;
    private Menu prevMenu;

    public EnrolledCoursesMenu(LMSFacade facade, Menu prevMenu, ArrayList<EnrolledCourse> courses) {
        if (courses.size() == 0){
            facade.setCurrentMenu(prevMenu);
            facade.getCurrentMenu().getSelection("You are not enrolled in any courses");
        }else{
            header = courses.get(courseIndex).toString();
        }
      
        this.facade = facade;
        this.courseIndex = 0;
        this.prevMenu = prevMenu;
        numCourses = courses.size();
        options = new String[]{"Next Course", "Previous Course", "View Comments", "Back"};
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
                break;
            case 4:
                facade.setCurrentMenu(prevMenu);
                facade.getCurrentMenu().getSelection();
                break;
        }
    }

}
