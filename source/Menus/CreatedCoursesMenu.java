package source.Menus;
import source.Course;
import source.LMSFacade;
import java.util.ArrayList;
import java.util.UUID;

public class CreatedCoursesMenu extends Menu{
    private ArrayList<Course> courses;
    private int courseIndex;
    private int numCourses;
    private Menu prevMenu;
    
    public CreatedCoursesMenu(LMSFacade facade, Menu prevMenu, ArrayList<UUID> coursesUUID) {
        this.courses = facade.getCoursesFromUUID(coursesUUID);
        this.facade = facade;
        this.courseIndex = 0;
        this.prevMenu = prevMenu;
        numCourses = courses.size();
        header = courses.get(courseIndex).toString();
        options = new String[]{"Next Course", "Previous Course", "View Comments", "Modify course", "Back"};
    }

    public void select(int selection) {
        switch(selection) {
            case 1:
                if (courseIndex + 1 >= numCourses) {
                    courseIndex = 0;
                }
                else courseIndex++;
                header = courses.get(courseIndex).toString();
                getSelection();
                break;
            case 2:
            if (courseIndex <= 0) {
                courseIndex = numCourses - 1;
            }
            else courseIndex--;
            header = courses.get(courseIndex).toString();
            getSelection();
                break;
            case 3:
                // view comments
                break;
            case 4:
            //modify course
            break;
            case 5:
            facade.setCurrentMenu(prevMenu);
            facade.getCurrentMenu().getSelection();
                break;
        }
    }

}